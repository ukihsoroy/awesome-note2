package org.ko.sso.util;

import org.assertj.core.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public final class SSOCheck {

    private static final String USERNAME = "user";

    private static final String PASSWORD = "123";

    public static boolean checkLogin (String username, String password) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean checkCookie (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (!Arrays.isNullOrEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if ("sso-domain".equals(cookie.getName()) && "SSO-LOGIN-SECURITY".equals(cookie.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    private SSOCheck(){}
}
