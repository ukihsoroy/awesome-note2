package org.ko.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 没办法获取参数的值
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle");
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        System.out.println(HandlerMethod.class.cast(o).getBean().getClass().getName());
        System.out.println(HandlerMethod.class.cast(o).getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        long startTime = Long.class.cast(httpServletRequest.getAttribute("startTime"));
        System.out.println("startTime: " + startTime);
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        long startTime = Long.class.cast(httpServletRequest.getAttribute("startTime"));
        System.out.println("startTime: " + startTime);
        System.out.println("afterCompletion");
        System.out.println("ex is: " + e);
    }
}
