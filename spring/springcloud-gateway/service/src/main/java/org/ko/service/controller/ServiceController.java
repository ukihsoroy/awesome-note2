package org.ko.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @GetMapping("hello")
    public String hello() {
        return "ok";
    }

}
