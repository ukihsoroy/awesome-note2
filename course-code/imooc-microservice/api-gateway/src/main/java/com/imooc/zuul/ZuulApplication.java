package com.imooc.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * description: UserEdgeApplication <br>
 * date: 2020/2/29 19:37 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }

}
