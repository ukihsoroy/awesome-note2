package org.ko.web;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

//SpringBoot启动
@SpringBootApplication
//开启Eureka注册中心连接
@EnableDiscoveryClient
//开启Feign客户端
@EnableFeignClients
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }

//    @Bean
    public Logger.Level feignLogger () {
        return Logger.Level.FULL;
    }
}
