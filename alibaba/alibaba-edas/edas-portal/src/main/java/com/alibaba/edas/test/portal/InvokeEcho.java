package com.alibaba.edas.test.portal;

import com.alibaba.edas.test.EdasDemoService;
import com.alibaba.edas.test.domain.Echo;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xiaofei.wxf on 2015/5/9.
 */
public class InvokeEcho extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String echo = req.getParameter("echo");
        if (null == echo || echo.isEmpty()) {
            echo = Long.toString(System.currentTimeMillis());
        }
        EdasDemoService edasDemoService = (EdasDemoService) CtxUtil.getService("edasDemoService");
        Integer round = CtxUtil.round(req);
        Echo ret = null;
        for (int i = 0; i < round; i++) {
            Echo arg = new Echo();
            arg.setData(echo);
            Echo tmp = edasDemoService.echo(arg);
            if (i == 0) {
                ret = tmp;
            }
        }
        resp.getWriter().println(round + "-this is v2" );
        resp.getWriter().println(JSON.toJSONString(JsonResp.newResp(true, ret.getData(), "")));
        resp.getWriter().flush();
    }
}
