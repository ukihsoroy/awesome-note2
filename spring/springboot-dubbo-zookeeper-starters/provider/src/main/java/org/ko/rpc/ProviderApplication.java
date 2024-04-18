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
public class ProviderApplication {
    public static void main( String[] args ) {
        SpringApplication.run(ProviderApplication.class, args);
    }
}
