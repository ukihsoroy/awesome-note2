<#list tables as t>
CREATE TABLE IF NOT EXISTS ${t.name}(
<#list t.columns as column>
    ${column.columnName ? upper_case} ${column.odpsType}<#if column_has_next>,</#if>
</#list>
);
</#list>
