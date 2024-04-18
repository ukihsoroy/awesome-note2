package org.ko.web;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * spring boot 应用启动程序
 */
//配置后自动扫描注解同名目录下的所有文件
@SpringBootApplication
//开启JSONDoc Api
@EnableJSONDoc
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
