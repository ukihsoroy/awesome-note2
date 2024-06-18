package com.panhai.sys.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>excel 帮助类</p>
 * @author #{申志远 13604261402}
 */
public final class ExcelHelper {

    private static final Logger log = LoggerFactory.getLogger(ExcelHelper.class);

    private static final int DATE_FORMAT_STYLE = 58;

    public static void export(List<String> heads, List<List<String>> rows, String output) throws Exception {
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("Sheet1");

            // 写入标题
            Row headRow = sheet.createRow(0);
            for(int i = 0; i < heads.size(); i++){
                Cell headCell = headRow.createCell(i);
                headCell.setCellValue(heads.get(i));
            }

            // 写入内容
            for(int i = 1; i <= rows.size(); i++){
                Row dataRow = sheet.createRow(i);

                List<String> line = rows.get(i - 1);
                for(int j = 0; j < line.size(); j++){
                    Cell cell = dataRow.createCell(j);
                    cell.setCellValue(line.get(j));
                }
            }

            // 解开以下注释添加头部过滤器
            sheet.createFreezePane(0, 1);

            for(int i = 0; i < heads.size(); i++){
                sheet.autoSizeColumn(i);
            }
            wb.write(new FileOutputStream(new File(output)));
            wb.close();
            log.info("exported {}", output);
        }

    }

    public static String getStringCellValue(Cell cell){
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue();
    }

    public static String getCellValue(Cell cell){
        String value = "";

        if(cell != null){
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case FORMULA:
                    value = getFormulaCellValue(cell);
                    break;
                case NUMERIC:
                    value = getStringForNumeric(cell);
                    break;
                case BOOLEAN:
                    value = String.valueOf(cell.getBooleanCellValue());
                    break;
                case ERROR:
                    value = String.valueOf(cell.getErrorCellValue());
                    break;
                case _NONE:
                    value = cell.getStringCellValue();
                    break;
                case BLANK:
                    value = cell.getStringCellValue();
                    break;
                default:
                    break;
            }

        }

        return StringUtils.trimToEmpty(value);
    }

    private static String getFormulaCellValue(Cell cell) {
        String value;
        try {
            value = cell.getStringCellValue();
        }
        catch(Exception e){
            log.error("读取Excel异常>>>", e);
            value = String.valueOf(cell.getNumericCellValue());
        }
        return value;
    }

    /**
     * 获取数字类型值
     * @param cell 单元格
     * @return 值
     */
    private static String getStringForNumeric(Cell cell) {
        String value;
        if(HSSFDateUtil.isCellDateFormatted(cell)){
            // 日期格式：处理yyyy-MM-dd, d/m/yyyy h:mm, HH:mm 等不含文字的日期格式
            String format = null;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                format = "HH:mm";
            }
            else if(cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm:ss")){
                format = "HH:mm:ss";
            }
            else {
                format = "yyyy-MM-dd HH:mm:ss";
            }
            Date date = cell.getDateCellValue();
            value = DateFormatUtils.format(date, format);
        }
        else if (cell.getCellStyle().getDataFormat() == DATE_FORMAT_STYLE) {
            // 自定义日期格式：处理yyyy年m月d日,h时mm分,yyyy年m月等含文字的日期格式
            double v = cell.getNumericCellValue();
            Date date = DateUtil.getJavaDate(v);
            value = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        }
        else {
            BigDecimal decimal = BigDecimal.valueOf(cell.getNumericCellValue());
            value = decimal.toPlainString();
        }
        return value;
    }

    public static String formatDate(Date date){
        return date == null ? "" : DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    public static String formatDateTime(Date date){
        return date == null ? "" : DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getStringValue(Object o){
        return o == null ? "" : StringUtils.trimToEmpty(String.valueOf(o));
    }

    public static byte[] file2byte(String filePath)
    {
        byte[] buffer = null;
        File file = new File(filePath);
        try (FileInputStream fis = new FileInputStream(file)) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            bos.close();
            buffer = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    private ExcelHelper(){}
}
