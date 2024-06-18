package io.ukihsoroy.gatling.source.mysql;

import io.ukihsoroy.gatling.source.DatasourceProperties;

/**
 * @author K.O
 */
public class MysqlProperties extends DatasourceProperties {

    /**
     * 数据库连接地址
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username = "root";

    /**
     * 数据库密码
     */
    private String password;

    /**
     * 数据库端口
     */
    private Integer port = 3306;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
