package com.alibaba.edas.test.portal;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by xiaofei.wxf on 2015/5/11.
 */
public class StartupListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        if(null == ctx) {
            System.err.println("AppCtx is null");
        } else {
            CtxUtil.setAppCtx(ctx);
            CtxUtil.init();
            System.err.println("AppCtx is set");
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
