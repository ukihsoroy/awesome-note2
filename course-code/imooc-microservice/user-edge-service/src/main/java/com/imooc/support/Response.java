package com.imooc.support;

import java.io.Serializable;

/**
 * description: Response <br>
 * date: 2020/2/29 19:48 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Response<T> implements Serializable {

    public static final Response SUCCESS = new Response("0", "success");
    public static final Response USERNAME_PASSWORD_INVALID = new Response("1001", "username or password invalid.");
    public static final Response MOBILE_OR_EMAIL_REQUIRED = new Response("1002", "mobile or email required.");
    public static final Response SEND_VERIFY_CODE_FAILED = new Response("1003", "send verify code failed.");
    public static final Response VERIFY_CODE_INVALID = new Response("1004", "verify code invalid.");

    private String code;
    private String message;
    private T data;

    public Response(T data) {
        this.code = "0";
        this.message = "success";
        this.data = data;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
