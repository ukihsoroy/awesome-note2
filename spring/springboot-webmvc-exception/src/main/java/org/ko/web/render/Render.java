package org.ko.web.render;

/**
 * REST ful 统一处理返回状态
 * @param <T>
 */
public class Render<T> {

    private String code;

    private String message;

    private String url;

    private T data;

    public Render(ApiCode apiCode) {
        this.code = apiCode.getCode();
        this.message = apiCode.getMessage();
    }

    public Render() {
        this.code = ApiCode.SUCCESS.getCode();
        this.message = ApiCode.SUCCESS.getMessage();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
