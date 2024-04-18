package org.ko.mybatis.exception;

import org.ko.mybatis.support.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class BasicExceptionController {

    /**
     * 自定义异常处理
     * @param ge
     * @param webRequest
     * @return
     */
    @ExceptionHandler(GlobalException.class)
    public Response<?> globalException(GlobalException ge, WebRequest webRequest) {
        return new Response<>(ge.getCode(), ge.getMessage(), null);
    }


    /**
     * exception 异常处理，这个是范围最大的
     * @param e
     * @param webRequest
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Response<?> validateException(Exception e, WebRequest webRequest) {
        return new Response<>("500", e.getMessage(), null);
    }

}
