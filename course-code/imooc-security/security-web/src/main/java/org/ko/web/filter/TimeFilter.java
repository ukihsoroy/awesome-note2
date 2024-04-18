package org.ko.web.filter;


import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * j2ee 来定义的规范
 * 在filter里不知道, 请求是在那里处理
 */
//@Component
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start.");
        long startTime = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("time: " + (new Date().getTime() - startTime));
        System.out.println("time filter end.");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy.");
    }
}
