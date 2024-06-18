package org.ko.web.exception;

import org.ko.web.render.ApiCode;

/**
 * 自定义异常
 */
public class ApplicationException extends Exception {

    private String code;

    public ApplicationException(ApiCode apiCode) {
        super(apiCode.getMessage());
        this.code = apiCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
