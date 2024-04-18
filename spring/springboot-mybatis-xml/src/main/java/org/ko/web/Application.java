package org.ko.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 应用启动程序
 */
@SpringBootApplication  //配置后自动扫描注解同名目录下的所有文件
@MapperScan("org.ko.web.dao.mapper")    //配置MyBatis接口扫描
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
