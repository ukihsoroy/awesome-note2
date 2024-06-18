package org.ko.sso.controller;

import org.assertj.core.util.Arrays;
import org.ko.sso.util.SSOCheck;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping
    public String index () {
        return "login";
    }

    @GetMapping("/login")
    public String login (String username, String password, HttpServletResponse response) throws IOException {
        boolean check = SSOCheck.checkLogin(username, password);
        String result = "0";
        if (check) {
            result = "1";
        }
        response.getWriter().print(result);
        response.getWriter().close();
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
                    Map<String, String> params = new HashMap<>();
                    params.put("cookieName", cookie.getName());
                    params.put("cookieValue", cookie.getValue());
                    String result = doGet("http://www.check.com:8081/check", params);
                    if ("1".equals(result)) {
                        return "demo1";
                    }
                }
            }
        }
        map.addAttribute("gotoUrl", "http://www.demo1.com:8081/demo1");
        map.addAttribute("path", "demo1");
        return "login";
    }

    @PostMapping("demo1/login")
    public String demo1Login (String username, String password, ModelMap map) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        String result = doGet("http://www.check.com:8081/login", params);
        if ("1".equals(result)) {
            List<String> urls = java.util.Arrays.asList(
                    "http://www.demo1.com:8081/demo1/add",
                    "http://www.demo2.com:8081/demo2/add"
            );
            map.addAttribute("urls", urls);
            return "demo1";
        }
        return "login";
    }

    @GetMapping("demo1/add")
    public void demo1Cookie (HttpServletResponse response) {
        Cookie loginCookie = new Cookie("sso-domain", "SSO-LOGIN-SECURITY");
        loginCookie.setPath("/");
        response.addCookie(loginCookie);
    }

    @GetMapping("demo2")
    public String demo2 (HttpServletRequest request, ModelMap map) {
        Cookie[] cookies = request.getCookies();
        if (!Arrays.isNullOrEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if ("sso-domain".equals(cookie.getName())) {
                    Map<String, String> params = new HashMap<>();
                    params.put("cookieName", cookie.getName());
                    params.put("cookieValue", cookie.getValue());
                    String result = doGet("http://www.check.com:8081/check", params);
                    if ("1".equals(result)) {
                        return "demo2";
                    }
                }
            }
        }
        map.addAttribute("gotoUrl", "http://www.demo2.com:8081/demo2");
        map.addAttribute("path", "demo2");
        return "login";
    }

    @PostMapping("demo2/login")
    public String demo2Login (String username, String password, ModelMap map) {
        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        String result = doGet("http://www.check.com:8081/login", params);
        if ("1".equals(result)) {
            List<String> urls = java.util.Arrays.asList(
                    "http://www.demo1.com:8081/demo1/add",
                    "http://www.demo2.com:8081/demo2/add"
            );
            map.addAttribute("urls", urls);
            return "demo2";
        }
        return "login";
    }

    @GetMapping("demo2/add")
    public void demo2Cookie (HttpServletResponse response) {
        Cookie loginCookie = new Cookie("sso-domain", "SSO-LOGIN-SECURITY");
        loginCookie.setPath("/");
        response.addCookie(loginCookie);
    }

    private String doGet (String url, Map<String, String> map) {
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection connection = null;
        try {
            StringBuffer ts = new StringBuffer(url).append("?");
            map.forEach((k, v) -> ts.append(k).append("=").append(v).append("&"));
            url = ts.substring(0, ts.length() - 1);
            URL urls = new URL(url);
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
