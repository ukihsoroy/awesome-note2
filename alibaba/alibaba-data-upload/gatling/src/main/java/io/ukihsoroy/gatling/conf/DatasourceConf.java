package io.ukihsoroy.gatling.conf;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import io.ukihsoroy.gatling.properties.AutomationDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author K.O
 */
@Configuration
@EnableConfigurationProperties(AutomationDataSourceProperties.class)
public class DatasourceConf {

    @Autowired
    private AutomationDataSourceProperties properties;

    /**
     * 获取mysql数据库连接
     * @return
     */
    private Map<String, JdbcTemplate> jdbcTemplates(String database) {
        Map<String, JdbcTemplate> jdbcTemplates = new HashMap<>(16);

        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setDatabaseName(database);
        mysqlDataSource.setPort(properties.getMysql().getPort());
        mysqlDataSource.setUser(properties.getMysql().getUsername());
        mysqlDataSource.setPassword(properties.getMysql().getPassword());
        return jdbcTemplates;
    }

}
