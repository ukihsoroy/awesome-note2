package org.ko.web.base;

public class Render<T> extends BaseBean{

    private String code;

    private String message;

    private T data;

    public Render(ApiCode apiCode) {
        this(apiCode.getCode(), apiCode.getMessage());
    }

    public Render(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Render() {
        this(ApiCode.SUCCESS);
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
