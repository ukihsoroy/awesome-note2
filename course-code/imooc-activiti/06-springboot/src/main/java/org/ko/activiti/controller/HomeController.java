package org.ko.activiti.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: HomeController <br>
 * date: 2020/2/18 19:58 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@RestController
public class HomeController {

    @RequestMapping("home")
    public String home() {
        return "Hello, world!";
    }
}
