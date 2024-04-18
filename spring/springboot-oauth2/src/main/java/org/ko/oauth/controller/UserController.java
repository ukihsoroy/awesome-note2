package org.ko.oauth.controller;

import org.ko.oauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;


    @ResponseBody
    @RequestMapping("ko/hello")
    public String koHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello World K.O！";
    }

    @ResponseBody
    @RequestMapping("user/hello")
    public String testHello() {
        return "Hello World test";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "Hello World";
    }


}