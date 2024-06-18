package org.ko.poi.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.ko.poi.excel.annotation.ExcelHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

import static org.ko.poi.excel.ExcelHelper.PATTERN.*;

/**
 * <p>Excel import/export helper.</p>
 * @author #{K.O you_leet@foxmail.com}
 */
public final class ExcelHelper {

	private static final Logger log = LoggerFactory.getLogger(ExcelHelper.class);
	
	private static final int DATE_FORMAT_STYLE = 58;


	/**
	 * <p>读取Excel</p>
	 * @param path 文件路径
	 * @return Map #key 行号 #value 对应列的值, 需要自行format
	 * @throws IOException
	 */
	public static Map<Integer, List<String>> readExcel(String path) throws IOException {
		return readExcel(path, null);
	}

	/**
	 * <p>读取Excel</p>
	 * @param path 文件路径
	 * @param checkExcel 校验
	 * @return Map #key 行号 #value 对应列的值, 需要自行format
	 * @throws IOException
	 */
	public static Map<Integer, List<String>> readExcel(String path, CheckExcel checkExcel) throws IOException {
		Map<Integer, List<String>> data = new HashMap<>();
		FileInputStream file = new FileInputStream(new File(path));
		try (Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheetAt(0);
			//Excel格式校验
			if (Objects.nonNull(checkExcel)) {
				checkExcel.check(sheet);
			}
			int i = 0;
			for (Row row : sheet) {
				data.put(i, new ArrayList<>());
				for (Cell cell : row) {
					data.get(i).add(getCellValue(cell));
				}
				i++;
			}
		}
		return data;
	}

	/**
	 * <p>读取Excel</p>
	 * @param path 文件路径
	 * @param clazz 返回的对象类型
	 * @param checkExcel 检查excel
	 * @param <T> 泛型
	 * @return 直接返回解析后的对象
	 * @throws IOException
	 */
	public static <T> List<T> readExcel (String path, Class<T> clazz, CheckExcel checkExcel) throws IOException {
		FileInputStream file = new FileInputStream(new File(path));
		List<T> data = new ArrayList<>();
		try (Workbook workbook = new XSSFWorkbook(file)) {
			Sheet sheet = workbook.getSheetAt(0);

			if (Objects.nonNull(checkExcel)) {
				checkExcel.check(sheet);
			}
			Map<String, Field> fieldMap = reflectFields(clazz);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				T target = null;
				try {
					target = clazz.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				for (int j = 1; j <= fieldMap.size(); j++) {
					Cell cell = row.getCell(j);
					Field field = fieldMap.get(String.valueOf(j));
					field.setAccessible(true);
					try {
						field.set(target, fieldValue(field, cell));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
				data.add(target);
			}
		}
		return data;
	}

	/**
	 * <p>导出Excel</p>
	 * @param heads 表格头(列名)
	 * @param rows 数据内容
	 * @param output 输出文件路径
	 * @throws IOException
	 */
	public static void export (List<String> heads, List<List<String>> rows, String output) throws IOException {

		try (Workbook wb = new XSSFWorkbook()) {
			Sheet sheet = wb.createSheet("Sheet1");

			// 写入标题
			Row headRow = sheet.createRow(0);
			// 标题格式
			CellStyle headerStyle = builderHeaderStyle(wb);
			for(int i = 0; i < heads.size(); i++){
				Cell headCell = headRow.createCell(i);
				headCell.setCellValue(heads.get(i));
				headCell.setCellStyle(headerStyle);
			}

			// 正文格式
			CellStyle contentStyle = builderContentStyle(wb);
			// 写入内容
			for(int i = 1; i <= rows.size(); i++){
				Row dataRow = sheet.createRow(i);

				List<String> line = rows.get(i - 1);
				for(int j = 0; j < line.size(); j++){
					Cell cell = dataRow.createCell(j);
					cell.setCellValue(line.get(j));
					cell.setCellStyle(contentStyle);
				}
			}

			sheet.createFreezePane(0, 1);

			for(int i = 0; i < heads.size(); i++){
				sheet.autoSizeColumn(i);
			}

			wb.write(new FileOutputStream(new File(output)));
			log.info("exported {}", output);
		}
	}

	/**
	 * <p>Excel导出</p>
	 * @param rows 数据内容
	 * @param output 文件路径
	 * @param clazz 列表泛型
	 * @param <T> 模版泛型
	 * @throws IOException IO异常
	 */
	public static <T> void export (List<T> rows, String output, Class<T> clazz) throws IOException {
		//表格头
		List<String> header = excelHeader(clazz);
		//表格内容
		List<List<String >> content = excelContent(rows, clazz);
		export(header, content, output);
	}

	public static String formatDate(Date date){
		return date == null ? "" : DateFormatUtils.format(date, YYYY_MM_DD);
	}

	public static String formatDateTime(Date date){
		return date == null ? "" : DateFormatUtils.format(date, YYYY_MM_DD_HH_MM_SS);
	}

	public static String getStringValue(Object o){
		return o == null ? "" : StringUtils.trimToEmpty(String.valueOf(o));
	}

	private static Object fieldValue (Field field, Cell cell) {
		String value = getCellValue(cell);
		Object result = null;
		if (field.getType().equals(byte.class) || field.getType().equals(Byte.class)) {
			result = Byte.parseByte(value);
		} else if (field.getType().equals(short.class) || field.getType().equals(Short.class)) {
			result = Short.parseShort(value);
		} else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
			result = Integer.parseInt(value);
		} else if (field.getType().equals(double.class) || field.getType().equals(Double.class)) {
			result = Double.parseDouble(value);
		} else if (field.getType().equals(long.class) || field.getType().equals(Long.class)) {
			result = Long.parseLong(value);
		} else if (field.getType().equals(BigDecimal.class)) {
			result = new BigDecimal(value);
		} else if (field.getType().equals(Date.class)) {
			try {
				result = DateUtils.parseDate(value, YYYY_MM_DD_HH_MM_SS);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else {
			result = value;
		}
		return result;
	}

	private static CellStyle builderHeaderStyle (Workbook workbook) {
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		headerStyle.setFont(builderHeaderFont(workbook));
		return headerStyle;
	}

	private static XSSFFont builderHeaderFont (Workbook workbook) {
		XSSFFont font = ((XSSFWorkbook) workbook).createFont();
		font.setFontName("Euphemia");
		font.setFontHeightInPoints((short) 16);
		font.setBold(true);
		return font;
	}

	private static CellStyle builderContentStyle (Workbook workbook) {
		CellStyle contentStyle = workbook.createCellStyle();
		contentStyle.setWrapText(true);
		return contentStyle;
	}

	private static String getCellValue(Cell cell){
		String value = "";
		
		if(cell != null){
			switch (cell.getCellTypeEnum()) {
				case STRING:
					value = cell.getStringCellValue();
					break;
				case BOOLEAN:
					value = String.valueOf(cell.getBooleanCellValue());
					break;
				case FORMULA:
					value = formatFormulaValue(cell);
					break;
				case NUMERIC:
					value = formatNumericValue(cell);
					break;
				case ERROR:
					value = String.valueOf(cell.getErrorCellValue());
					break;
				default:
					break;
			}
		}
		
		return StringUtils.trimToEmpty(value);
	}

	private static String formatFormulaValue(Cell cell) {
		String value;
		try{
            value = cell.getStringCellValue();
        }catch(Exception e){
            value = String.valueOf(cell.getNumericCellValue());
        }
		return value;
	}

	private static String formatNumericValue(Cell cell) {
		String value;
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
            // 日期格式：处理yyyy-MM-dd, d/m/yyyy h:mm, HH:mm 等不含文字的日期格式
            String format;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat(H_MM)) {
                format = HH_MM;
            } else if(cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm:ss")) {
                format = HH_MM_SS;
            } else {
                format = YYYY_MM_DD_HH_MM_SS;
            }
            Date date = cell.getDateCellValue();
            value = DateFormatUtils.format(date, format);
        } else if (cell.getCellStyle().getDataFormat() == DATE_FORMAT_STYLE) {
            // 自定义日期格式：处理yyyy年m月d日,h时mm分,yyyy年m月等含文字的日期格式
            double v = cell.getNumericCellValue();
            Date date = DateUtil.getJavaDate(v);
            value = DateFormatUtils.format(date, YYYY_MM_DD_HH_MM_SS);
        } else {
            BigDecimal decimal = BigDecimal.valueOf(cell.getNumericCellValue());
            value = decimal.toPlainString();
        }
		return value;
	}

	private static Map<String, Field> reflectFields (Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		LinkedHashMap<String, Field> fieldMap = new LinkedHashMap<>();
		//取出Excel所有字段
		for (Field field : fields) {
			ExcelHeader column = field.getAnnotation(ExcelHeader.class);
			if (Objects.nonNull(column)) {
				fieldMap.put(column.index(), field);
			}
		}
		return fieldMap;
	}

	public static List<String> excelHeader (Class clazz) {
		Field[] fields = clazz.getDeclaredFields();
		String[] contains = new String[fields.length + 1];
		contains[0] = "序号";
		//取出Excel所有字段
		for (Field field : fields) {
			ExcelHeader column = field.getAnnotation(ExcelHeader.class);
			if (Objects.nonNull(column)) {
				contains[Integer.parseInt(column.index())] = column.name();
			}
		}
		List<String> header = new ArrayList<>();
		for (String name: contains) {
			if (Objects.isNull(name)) break;
			header.add(name);
		}
		return header;
	}

	private static <T> List<List<String>> excelContent (List<T> data, Class<T> clazz) {
		Map<String, Field> fieldMap = reflectFields(clazz);
		List<List<String>> rows = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			List<String> row = new ArrayList<>();
			row.add(String.valueOf(i + 1));
			for (int j = 1; j <= fieldMap.size(); j++) {
				Field field = fieldMap.get(String.valueOf(j));
				field.setAccessible(true);
				try {
					row.add(converterString(field.get(data.get(i))));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			rows.add(row);
		}
		return rows;
	}

	private static String converterString(Object target) {
		String value;
		if (target instanceof Number) {
			value = String.valueOf(target);
		} else if (target instanceof Date) {
			value = formatDateTime(Date.class.cast(target));
		} else {
			value = String.class.cast(target);
		}
		return value;
	}

	private ExcelHelper(){}

	public interface CheckExcel {
		void check(Sheet sheet);
	}

	final class PATTERN {

		static final String H_MM = "h:mm";
		static final String HH_MM = "HH:mm";
		static final String HH_MM_SS = "HH:mm:ss";
		static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
		static final String YYYY_MM_DD = "yyyy-MM-dd";

		private PATTERN() {}
	}
}
