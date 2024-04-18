package org.ko.web.exception;

import org.ko.web.render.Render;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常统一处理监听
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = ApplicationException.class)
    @ResponseBody
    public Render<String> jsonErrorHandler(HttpServletRequest request, ApplicationException e) throws Exception {
        Render<String> render = new Render<>();
        render.setCode(e.getCode());
        render.setMessage(e.getMessage());
        render.setData("Some Data");
        render.setUrl(request.getRequestURL().toString());
        return render;
    }


}
