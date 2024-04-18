package org.ko.mvc.adapter;

import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller实现类会自动适配
 */
public class ControllerHandlerAdapter implements HandlerAdapter {

    /**
     * 判断是否需要适配
     * 如果对象是Controller实现类, 执行handler方法
     * @param handler
     * @return
     */
    public boolean supports(Object handler) {
        return handler instanceof Controller;
    }

    /**
     * Spring代理触发适配器, 下一步进入Controller
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return Controller.class.cast(handler).handleRequest(request, response);
    }

    public long getLastModified(HttpServletRequest request, Object handler) {
        return 0;
    }
}
