package org.ko.rpc;

import org.ko.rpc.service.ConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
//SpringBoot启动
@SpringBootApplication
public class ConsumerApplication {

    private static final Logger _LOGGER = LoggerFactory.getLogger(ConsumerApplication.class);

    public static void main( String[] args ) {

        ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class, args);
        ConsumerService consumerService = run.getBean(ConsumerService.class);
        _LOGGER.info("ConsumerService.getUser Return: {}", consumerService.getUser().toString());
    }
}
