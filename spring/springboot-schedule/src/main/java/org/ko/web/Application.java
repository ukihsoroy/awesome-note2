package org.ko.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * spring boot 应用启动程序
 */
@SpringBootApplication  //配置后自动扫描注解同名目录下的所有文件
@EnableScheduling       //定时任务开启
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
