package org.ko.web.controller;

import org.ko.web.exception.ApplicationException;
import org.ko.web.render.ApiCode;
import org.ko.web.render.Render;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    /**
     * 测试是否根据抛出异常类型匹配
     * @return
     * @throws Exception
     */
    @GetMapping("/e1")
    public String error1() throws Exception {
        throw new ApplicationException(ApiCode.ERROR);
    }

    /**
     * 测试是否根据抛出异常类型匹配
     * @return
     * @throws ApplicationException
     */
    @GetMapping("/e2")
    public String error2 () throws ApplicationException {
        throw new ApplicationException(ApiCode.ERROR);
    }

    /**
     * 测试页面渲染错误
     * @return
     * @throws Exception
     */
    @GetMapping("/e3")
    public String error3() throws Exception {
        throw new Exception("This is very bad!");
    }

    /**
     * 渲染页面
     * @return
     * @throws Exception
     */
    @GetMapping("/r1")
    public ModelAndView index () throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "world");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 正常返回JSON
     * @return
     */
    @GetMapping("/j1")
    @ResponseBody
    public Render<String> render () {
        return new Render<>();
    }

    /**
     * 返回带数据JSON
     * @return
     */
    @GetMapping("/j2")
    @ResponseBody
    public Render<String> hello () {
        Render<String> render = new Render<>();
        render.setData("Hello World!");
        return render;
    }



}
