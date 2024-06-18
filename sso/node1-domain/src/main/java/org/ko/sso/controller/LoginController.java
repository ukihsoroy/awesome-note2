package org.ko.sso.controller;

import org.ko.sso.util.SSOCheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping
    public String index () {
        return "login";
    }

    @PostMapping("/login")
    public String login (String username, String password, String gotoUrl, HttpServletResponse response) {
        boolean check = SSOCheck.checkLogin(username, password);
        if (check) {
            Cookie loginCookie = new Cookie("sso-domain", "SSO-LOGIN-SECURITY");
            loginCookie.setPath("/");
            response.addCookie(loginCookie);
            return gotoUrl;
        }
        return "login";
    }

    @GetMapping("demo1")
    public String demo1 (HttpServletRequest request, ModelMap map) {
        if (SSOCheck.checkCookie(request)) {
            return "demo1";
        }
        map.addAttribute("gotoUrl", "demo1");
        return "login";
    }

    @GetMapping("demo2")
    public String demo2 (HttpServletRequest request, ModelMap map) {
        if (SSOCheck.checkCookie(request)) {
            return "demo2";
        }
        map.addAttribute("gotoUrl", "demo2");
        return "login";
    }
}
