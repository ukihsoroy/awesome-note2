package org.ko.mybatis.exception;

/**
 * 自定义全局异常
 */
public class GlobalException extends RuntimeException {

    /**
     * 错误编码
     */
    private String code;

    public GlobalException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
