package org.ko.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/{name}")
    public ModelAndView hello(@PathVariable String name) {
        ModelAndView modelAndView = new ModelAndView();
        //添加返回页面数据
        modelAndView.addObject("name", name);
        //确定返回的视图
        modelAndView.setViewName("hello");
        return modelAndView;
    }

}
