package org.ko.oauth.support;

/**
 * Response通用返回类
 * @author zhiyuan.shen
 */
public class Response<T> {

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(T data) {
        this.code = 200;
        this.message = "SUCCESS";
        this.data = data;
    }

    public Response() {
        this.code = 200;
        this.message = "SUCCESS";
    }

    /**
     * 返回成功，并带有数据
     * @param data
     * @param <T>
     * @return
     */
    public static  <T> Response<T> ok(T data) {
        return new Response<>(data);
    }

    /**
     * 返回成功，无数据
     * @return
     */
    public static Response<?> ok() {
        return new Response<>();
    }

}
