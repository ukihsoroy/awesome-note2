package org.ko.web.render;

/**
 * 请求状态统一处理
 */
public enum ApiCode {

    SUCCESS("0", "SUCCESS"),
    ERROR("1", "This is very bad!")
    ;

    private String code;

    private String message;

    ApiCode (String code, String message) {
        this.code = code;
        this.message = message;
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
}
