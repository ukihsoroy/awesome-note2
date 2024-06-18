package org.ko.zuul;

import org.ko.zuul.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


//SpringCloud应用启动(Boot, Eureka, Hystrix启动)
@SpringCloudApplication
//开启Zuul API网关服务
@EnableZuulProxy
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    /**
     * 配置Zuul过滤器
     * @return
     */
    @Bean
    public AccessFilter accessFilter () {
        return new AccessFilter();
    }
}
