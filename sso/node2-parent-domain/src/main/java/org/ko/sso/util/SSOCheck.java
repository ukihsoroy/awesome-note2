package org.ko.sso.util;

public final class SSOCheck {

    private static final String USERNAME = "user";

    private static final String PASSWORD = "123";

    public static boolean checkLogin (String username, String password) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            return true;
        }
        return false;
    }

    public static boolean checkCookie (String cookieName, String cookieValue) {
        if ("sso-domain".equals(cookieName) && "SSO-LOGIN-SECURITY".equals(cookieValue)) {
            return true;
        }
        return false;
    }

    private SSOCheck(){}
}
