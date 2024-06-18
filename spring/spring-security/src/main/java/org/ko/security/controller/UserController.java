package org.ko.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {


    @RequestMapping("/")
    public ModelAndView login () {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "K.O");
        mav.setViewName("index");
        return mav;
    }
}
