package org.ko.poi.excel.annotation;

import org.ko.poi.excel.type.CellStyleType;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelHeader {

    /**
     * 导入导出索引
     * @return
     */
    String index() default "";

    /**
     * 导入导出字段名称
     * @return
     */
    String name() default "";

    /**
     * 表格头样式
     * @return
     */
    CellStyleType headerStyle() default CellStyleType.HEADER_STYLE;

    /**
     * 内容样式
     * @return
     */
    CellStyleType contentStyle() default CellStyleType.CONTENT_STYLE;

}
