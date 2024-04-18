package com.alibaba.edas.test.portal;

import com.alibaba.edas.test.EdasDemoService;
import com.alibaba.edas.test.domain.Echo;
import com.alibaba.edas.test.domain.Now;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaofei.wxf on 2015/5/9.
 */
public class InvokeNow extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EdasDemoService edasDemoService = (EdasDemoService) CtxUtil.getService("edasDemoService");
        Integer round = CtxUtil.round(req);
        if (round <= 0) {
            round = 1;
        }
        Now ret = null;
        for (int i = 0; i < round; i++) {
            Now tmp = edasDemoService.now();
            if (i == 0) {
                ret = tmp;
            }
        }
        resp.getWriter().println(round);
        resp.getWriter().println(JSON.toJSONString(JsonResp.newResp(true, ret.getData(), "")));
        resp.getWriter().flush();
    }
}
