package org.ko.rest.controller;

import org.ko.rest.domain.Menu;
import org.ko.rest.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {

    @Autowired private MenuService menuService;

    @GetMapping("/hello")
    public String hello () {
        return menuService.hello();
    }

    @GetMapping
    public List<Menu> queryList() {
        return menuService.getAll();
    }

}
