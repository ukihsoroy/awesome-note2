package org.ko.mvc.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * SpringMVC 拦截器
 * 三个阶段
 * 1) 请求到Controller前, #{@link #preHandle}
 * 2) Controller执行后, DispatcherServlet视图渲染前, #{@link #postHandle}
 * 3) 视图渲染后, #{@link #afterCompletion}
 * 4) 2,3都依赖1结果为true
 */
public class GlobalInterceptor implements HandlerInterceptor {

    /**
     * 请求到Controller前执行
     * 返回false则请求结束, 不会进入Controller
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        if (!StringUtils.isEmpty(url)) {
            try {
                url = url.replaceAll("/", "");
                if (Integer.valueOf(url) > 0) {
                    request.setAttribute("code", "Hello, World!");
                }
            } catch (NumberFormatException nfe) {
                System.out.println(nfe.getMessage());
            }
        }
        return true;
    }


    /**
     * Controller执行后, DispatcherServlet视图渲染前执行
     * 用来处理一些Model数据等
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 视图渲染完成后执行
     * 用来进行资源清理等
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
