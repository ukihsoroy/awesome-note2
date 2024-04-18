package org.ko.web.chapter4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("ch4")
public class Ch4Controller {

    @RequestMapping("arg1")
    public String arg1(@RequestParam String name) {
        return "hello" + name;
    }

}
