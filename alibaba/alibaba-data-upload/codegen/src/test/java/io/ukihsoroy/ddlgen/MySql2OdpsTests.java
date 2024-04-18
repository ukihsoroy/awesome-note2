package io.ukihsoroy.ddlgen;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.ukihsoroy.schemagen.bean.Table;
import io.ukihsoroy.schemagen.source.mysql.MysqlSchemagen;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MySql2OdpsTests {

    private String databaseName = "sigma_server";

    private MysqlDataSource mysqlDataSource;

    @Autowired
    private freemarker.template.Configuration freeMarkerConfiguration;

    @Before
    public void mysqlSource() {
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setDatabaseName("sigma_server");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setUser("root");
        mysqlDataSource.setPassword("tiger");
    }

    @Test
    public void test1() throws IOException, TemplateException {
        MysqlSchemagen mysqlSchemagen = new MysqlSchemagen(mysqlDataSource);
        String name = "t_send_code_log";
        String root = "D://ddl/sigma_server/";
        Table table = mysqlSchemagen.extractRecord(name);
        FileUtils.forceMkdir(new File("D://ddl/sigma_server"));
        Template template = freeMarkerConfiguration.getTemplate("create_table_ddl.ftl");
        Writer out = new OutputStreamWriter(new FileOutputStream(new File(root + name + "-ddl.sql")), "UTF-8");
        Map<String, Object> model = new HashMap<>();
        model.put("table", table.getName());
        model.put("columns", table.getColumns());
        model.put("now", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
        template.process(model, out);
    }

    @Test
    public void test2() throws IOException, TemplateException {
        MysqlSchemagen mysqlSchemagen = new MysqlSchemagen(mysqlDataSource);
        String[] names = new String[]{"t_send_code_log", "t_request_log"};
        String root = "D://ddl/";
        List<Table> tables = new ArrayList<>();
        for (String name : names) {
            Table table = mysqlSchemagen.extractRecord(name);
            tables.add(table);
        }
        FileUtils.forceMkdir(new File(root));
        Template template = freeMarkerConfiguration.getTemplate("create_tables_ddl.sql.ftl");
        Writer out = new OutputStreamWriter(new FileOutputStream(new File(root + "/" + databaseName + "_ddl.sql")), "UTF-8");
        Map<String, Object> model = new HashMap<>();
        model.put("tables", tables);
        model.put("now", DateFormatUtils.format(Calendar.getInstance(), "yyyy-MM-dd HH:mm:ss"));
        template.process(model, out);
    }

}
