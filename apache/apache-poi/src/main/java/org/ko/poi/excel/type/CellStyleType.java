package org.ko.poi.excel.type;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public enum  CellStyleType {

    HEADER_STYLE(workbook -> {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Euphemia");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);
        return headerStyle;
    }),

    CONTENT_STYLE(workbook -> {
        CellStyle contentStyle = workbook.createCellStyle();
        contentStyle.setWrapText(true);
        return contentStyle;
    })

    ;

    private ICellStyle cellStyle;

    CellStyleType(ICellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }

    public CellStyle get(Workbook workbook) {
        return cellStyle.get(workbook);
    }

    public interface ICellStyle {
        CellStyle get(Workbook workbook);
    }

}
