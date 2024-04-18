package org.ko.mvc.controller;

import org.ko.mvc.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 标记当前类为控制层
 * Spring扫描此注解
 * 将Mapping对应映射关系保存到容器
 */
@Controller
public class HelloController {

    //配置URL映射
    @GetMapping("/hello")
    public String hello (ModelMap model) {
        model.addAttribute("user", new User("K.O", new Date()));
        //返回视图名称
        return "hello";
    }

    @GetMapping("/{code}")
    public String mvc (@PathVariable("code") String code, ModelMap map, HttpServletRequest request) {
        String param = String.class.cast(request.getAttribute("code"));
        map.addAttribute("code", StringUtils.isEmpty(param) ? code : param);
        return "world";
    }
}
