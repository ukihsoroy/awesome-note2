package org.ko.nacos.discovery.provider.rest.controller;

import org.ko.nacos.discovery.provider.rest.entity.User;
import org.ko.nacos.discovery.provider.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
    public List<User> queryUserList() {
        return userService.queryUserList();
    }

    @GetMapping("{name}")
    public User queryUserByName(@PathVariable String name) {
        return userService.queryUserByName(name);
    }

    @PostMapping
    public String insertUser(@RequestBody User user) {
        return userService.insertUser(user);
    }

    @PutMapping("{name}")
    public String updateUser(@PathVariable String name, @RequestBody User user) {
        return userService.updateUser(name, user);
    }

    @DeleteMapping("{name}")
    public String removeUser(@PathVariable String name) {
        return userService.removeUser(name);
    }
}
