package org.ko.oauth.conf;

import com.google.common.base.Throwables;
import org.ko.oauth.support.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * 全局异常处理
 */
@RestControllerAdvice(annotations = RestController.class)
public class ApiExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandlerAdvice.class);
    /**
     * Handle exceptions thrown by handlers.
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response exception(Exception exception, WebRequest request) {
        Response errorResult =new Response();
        errorResult.setMessage(Throwables.getRootCause(exception).getMessage());
        errorResult.setCode(-1);
        logger.error("GlobalException====",exception);
        return errorResult;
    }
}
