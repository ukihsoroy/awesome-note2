package io.ukihsoroy.gatling.properties;

import io.ukihsoroy.gatling.source.mysql.MysqlProperties;
import io.ukihsoroy.gatling.source.maxcompute.MaxComputeProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author K.O
 */
@ConfigurationProperties(prefix = "automation.datasource")
public class AutomationDataSourceProperties {

    private MysqlProperties mysql = new MysqlProperties();

    private MaxComputeProperties maxCompute = new MaxComputeProperties();

    public MysqlProperties getMysql() {
        return mysql;
    }

    public void setMysql(MysqlProperties mysql) {
        this.mysql = mysql;
    }

    public MaxComputeProperties getMaxCompute() {
        return maxCompute;
    }

    public void setMaxCompute(MaxComputeProperties maxCompute) {
        this.maxCompute = maxCompute;
    }
}
