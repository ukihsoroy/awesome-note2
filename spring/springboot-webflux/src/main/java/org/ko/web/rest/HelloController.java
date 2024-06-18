package org.ko.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class HelloController {


    @GetMapping("/")
    public String getHello () {
        return "Hello World!";
    }

    @GetMapping("/mono")
    public Mono<String> hello () {
        return Mono.just("Hello World!");
    }
}
