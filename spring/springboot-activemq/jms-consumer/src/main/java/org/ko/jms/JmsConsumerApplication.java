package org.ko.jms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JmsConsumerApplication {

    public static void main( String[] args ) {
        SpringApplication.run(JmsConsumerApplication.class, args);
    }

}
