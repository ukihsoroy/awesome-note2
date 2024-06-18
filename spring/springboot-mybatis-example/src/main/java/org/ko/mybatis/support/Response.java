package org.ko.mybatis.support;

/**
 * web 通用返回类
 * @param <T>
 */
public class Response<T> {

    /**
     * 返回代码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(T data) {
        this.code = "200";
        this.message = "SUCCESS";
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
