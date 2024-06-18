package io.ukihsoroy.schemagen.source.mysql;

import com.google.common.base.CaseFormat;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.ukihsoroy.schemagen.bean.Column;
import io.ukihsoroy.schemagen.bean.Table;
import io.ukihsoroy.schemagen.source.AbstractSchemagen;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

/**
 * description: AbstractGenerator <br>
 * date: 2020/5/23 22:55 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class MysqlSchemagen extends AbstractSchemagen {

    private final JdbcTemplate jDBCTemplate = new JdbcTemplate();

    private MysqlDataSource mysqlDataSource = null;

    public MysqlSchemagen(MysqlDataSource dataSource) {
        jDBCTemplate.setDataSource(dataSource);
        mysqlDataSource = dataSource;
    }

    public List<String> findTableNames(String database) {
        return jDBCTemplate.queryForList(MysqlConstants.INFORMATION_SCHEMA_TABLES, String.class, database);
    }

    protected String findTableComment (String name) {
        String database = mysqlDataSource.getDatabaseName();
        return jDBCTemplate.queryForObject(MysqlConstants.INFORMATION_SCHEMA_TABLE_DETAIL, String.class, database, name);
    }

    public List<Column> findColumnByTableName (String name) {
        String database = mysqlDataSource.getDatabaseName();
        return jDBCTemplate.query(MysqlConstants.INFORMATION_SCHEMA_COLUMNS, (RowMapper<Column>) (rs, i) -> {

            String columnName = rs.getString(MysqlConstants.COLUMN_NAME);
            String columnType = rs.getString(MysqlConstants.DATA_TYPE).toLowerCase();
            Integer charLength = toInt(rs.getString(MysqlConstants.CHARACTER_MAXIMUM_LENGTH));
            Integer precision = toInt(rs.getString(MysqlConstants.NUMERIC_PRECISION));
            Integer scale = toInt(rs.getString(MysqlConstants.NUMERIC_SCALE));
            int len = charLength + precision + scale;
            if (scale != 0) {
                len = len + 1;
            }
            return new Column(
                    columnName,
                    mapUnderscoreToCamelCase(columnName),
                    columnType,
                    MysqlConverterJavaTypeHandler.format(columnType),
                    MysqlConverterOdpsTypeHandler.format(columnType),
                    MysqlConstants.PRI.equalsIgnoreCase(rs.getString(MysqlConstants.COLUMN_KEY)),
                    len,
                    StringUtils.trimToEmpty(rs.getString(MysqlConstants.COLUMN_COMMENT))
            );
        }, database, name);
    }


    /**
     * 下划线转驼峰
     * @param name
     * @return
     */
    protected String mapUnderscoreToCamelCase(String name) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, name);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jDBCTemplate;
    }

    @Override
    public Table extractRecord(String name) {
        //表名字
        String comment = findTableComment(name);

        //表字段
        List<Column> columns = findColumnByTableName(name);

        //创建表
        Table table = new Table();
        table.setName(name);
        table.setComment(comment);
        table.setColumns(columns);
        return table;
    }
}
