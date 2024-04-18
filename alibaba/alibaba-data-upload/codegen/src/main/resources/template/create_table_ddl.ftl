CREATE TABLE IF NOT EXISTS ${table}(
<#list columns as column>
    ${column.columnName ? upper_case} ${column.odpsType}<#if column_has_next>,</#if>
</#list>
);


