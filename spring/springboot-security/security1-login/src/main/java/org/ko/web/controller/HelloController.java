package org.ko.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @GetMapping("/info")
    public String info () {
        return "info";
    }

    @GetMapping("/login")
    public String login () {
        return "login";
    }

    @GetMapping("/")
    public String index () {
        return "index";
    }

}
