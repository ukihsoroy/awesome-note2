package org.ko.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//Spring Boot 程序
@SpringBootApplication
//向Eureka注册中心发布服务
@EnableEurekaClient
public class ComputeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComputeServiceApplication.class, args);
    }
}
