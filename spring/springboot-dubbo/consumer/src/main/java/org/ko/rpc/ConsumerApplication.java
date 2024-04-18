package org.ko.rpc;

import org.ko.rpc.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * Hello world!
 *
 */
//SpringBoot启动
@SpringBootApplication
//引入Dubbo配置文件
@ImportResource("dubbo/config.xml")
public class ConsumerApplication {

    private static final Logger _LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main( String[] args ) {

        ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class, args);
        UserService userService = run.getBean(UserService.class);
        _LOGGER.info("UserService.getUserName Return: {}", userService.getUserName());
    }
}
