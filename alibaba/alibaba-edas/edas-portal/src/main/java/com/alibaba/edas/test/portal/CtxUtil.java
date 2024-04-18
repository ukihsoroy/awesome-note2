package com.alibaba.edas.test.portal;

import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xiaofei.wxf on 2015/5/9.
 */
public class CtxUtil {
    private static ApplicationContext appCtx = null;

    public static void init() {

    }

    public static ApplicationContext getAppCtx() {
         return appCtx;
    }

    public static void setAppCtx(ApplicationContext appCtx) {
        CtxUtil.appCtx = appCtx;
    }

    public static Object getService(String name) {
        return appCtx.getBean(name);
    }



    public static Integer round(HttpServletRequest req) {
        String roundStr = req.getParameter("round");
        if (null == roundStr || roundStr.isEmpty()) {
           return 1;
        }
        Integer round = 1;
        try {
            round = Integer.valueOf(roundStr);
        }catch (Exception e) {
        }
        if (round <= 0) {
            return 1;
        }
        return round;
    }
}
