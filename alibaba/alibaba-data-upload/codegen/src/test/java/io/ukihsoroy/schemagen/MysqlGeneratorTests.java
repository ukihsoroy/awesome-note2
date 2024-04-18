package io.ukihsoroy.schemagen;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.ukihsoroy.schemagen.bean.Column;
import io.ukihsoroy.schemagen.bean.Table;
import io.ukihsoroy.schemagen.source.mysql.MysqlSchemagen;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MysqlGeneratorTests {

    private MysqlDataSource mysqlDataSource;

    private String name = "sigma_server";

    @Before
    public void mysqlSource() {
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setDatabaseName(name);
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("tiger");
    }

    @Test
    public void mysqlSchema() {
        MysqlSchemagen mysqlSchemagen = new MysqlSchemagen(mysqlDataSource);
        List<String> sigma_server = mysqlSchemagen.findTableNames(name);
        sigma_server.forEach(name -> {
            Table table = mysqlSchemagen.extractRecord(name);
            for (int i = 0; i < 10; i++) {
                String sql = buildInsertSql(table);
                JdbcTemplate jdbcTemplate = mysqlSchemagen.getJdbcTemplate();
                System.out.println(sql);
                jdbcTemplate.execute(sql);
            }
        });
    }

    private String buildInsertSql(Table table) {
        StringBuilder sql = new StringBuilder("INSERT INTO " + table.getName() + "(");
        StringBuilder values = new StringBuilder(" VALUES(");
        table.getColumns().forEach(column -> {
            sql.append(column.getColumnName()).append(",");
            values.append(mockValue(column)).append(",");
        });
        return sql.substring(0, sql.length() - 1) + ") " + values.substring(0, values.length() - 1) + ")";
    }

    private String mockValue(Column column) {
        switch (column.getColumnType()) {
            case "varchar":
            case "char":
            case "blob":
            case "text":
                String value = UUID.randomUUID().toString();
                return "'" + value.substring(0, value.length() > column.getLength() ? column.getLength() : value.length()) + "'";
            case "smallint":
                return String.valueOf(new Random().nextInt() % 10);
            case "int":
            case "tinyint":
            case "mediumint":
            case "bigint":
            case "float":
            case "double":
            case "decimal":
                String deci = String.valueOf(Math.abs(new Random().nextInt()));
                return "'" + deci.substring(0, deci.length() > Math.round(column.getLength() / 2) ? Math.round(column.getLength() / 2) : deci.length()) + "'";
            case "date":
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
                return "'" + sdf1.format(new Date()) + "'";
            case "datetime":
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
                return "'" + sdf2.format(new Date()) + "'";
            case "timestamp":
                return "'" + new Date().getTime() + "'";
            case "json":
                return "'{}'";
            default:
                return "";
        }
    }

}
