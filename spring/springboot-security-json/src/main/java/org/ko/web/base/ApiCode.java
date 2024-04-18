package org.ko.web.base;

public enum ApiCode {
    SUCCESS("0", "SUCCESS"),
    SESSION_TIMEOUT("1", "Please login.")
    ;

    private String code;

    private String message;

    ApiCode(String code, String message) {
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
