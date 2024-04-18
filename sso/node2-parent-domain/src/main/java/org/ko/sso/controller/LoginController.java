package org.ko.sso.controller;

import org.assertj.core.util.Arrays;
import org.ko.sso.util.SSOCheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class LoginController {

    @GetMapping
    public String index () {
        return "login";
    }

    @PostMapping("/login")
    public String login (String username, String password, String gotoUrl, HttpServletResponse response) throws IOException {
        boolean check = SSOCheck.checkLogin(username, password);
        if (check) {
            Cookie loginCookie = new Cookie("sso-domain", "SSO-LOGIN-SECURITY");
            loginCookie.setDomain("x.com");
            loginCookie.setPath("/");
            response.addCookie(loginCookie);
            response.sendRedirect(gotoUrl);
            return null;
        }
        return "login";
    }

    @GetMapping("check")
    public void checkCookie (String cookieName, String cookieValue, HttpServletResponse response) throws IOException {
        boolean check = SSOCheck.checkCookie(cookieName, cookieValue);
        String result = "0";
        if (check) {
            result = "1";
        }
        response.getWriter().print(result);
        response.getWriter().close();
    }

    @GetMapping("demo1")
    public String demo1 (HttpServletRequest request, ModelMap map) {
        Cookie[] cookies = request.getCookies();
        if (!Arrays.isNullOrEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if ("sso-domain".equals(cookie.getName())) {
                    String result = doGet("http://check.x.com:8081/check", cookie.getName(), cookie.getValue());
                    if ("1".equals(result)) {
                        return "demo1";
                    }
                }
            }
        }
        map.addAttribute("gotoUrl", "http://demo1.x.com:8081/demo1");
        return "login";
    }

    @GetMapping("demo2")
    public String demo2 (HttpServletRequest request, ModelMap map) {
        Cookie[] cookies = request.getCookies();
        if (!Arrays.isNullOrEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if ("sso-domain".equals(cookie.getName())) {
                    String result = doGet("http://check.x.com:8081/check", cookie.getName(), cookie.getValue());
                    if ("1".equals(result)) {
                        return "demo2";
                    }
                }
            }
        }
        map.addAttribute("gotoUrl", "http://demo2.x.com:8081/demo2");
        return "login";
    }

    private String doGet (String url, String cookieName, String cookieValue) {
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection connection = null;
        try {
            URL urls = new URL(url + "?cookieName=" + cookieName + "&cookieValue=" + cookieValue);
            connection = (HttpURLConnection)urls.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream in = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(isr);
            String bufferData;
            while ((bufferData = br.readLine()) != null) {
                buffer.append(bufferData);
            }
            br.close();
            isr.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return buffer.toString();
    }
}
