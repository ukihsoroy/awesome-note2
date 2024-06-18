package org.ko.security.core.properties;

public enum LoginType {

    /**
     * 使用spring默认重定向页面方式返回成功失败
     */
    REDIRECT,
    /**
     * 使用JSON返回登陆成功失败
     */
    JSON;
}
