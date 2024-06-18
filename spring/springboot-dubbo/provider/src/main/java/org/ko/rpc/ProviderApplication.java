package org.ko.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
//SpringBoot启动
@SpringBootApplication
//引入Dubbo配置文件
@ImportResource("dubbo/config.xml")
public class ProviderApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
