package org.ko.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HelloController {

    @GetMapping("/{name}")
    public String hello(@PathVariable("name") String name) {
        ModelMap map = new ModelMap();
        map.addAttribute("name", name);
        return "index";
    }

}
