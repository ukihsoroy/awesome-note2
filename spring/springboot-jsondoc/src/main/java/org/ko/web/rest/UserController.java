package org.ko.web.rest;

import org.jsondoc.core.annotation.*;
import org.ko.web.entity.User;
import org.ko.web.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
//配置JSONDoc接口组注释
@Api(name = "用户集成接口", description = "获取维护用户具体数据")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
    //单个方法注释
    @ApiMethod(summary = "获取全部用户", description = "获取全部用户")
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    @ApiMethod(summary = "新增用户", description = "添加用户")
    public Long save(
            @ApiBodyObject(clazz = User.class) @RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    @ApiMethod(summary = "通过ID获取用户", description = "通过ID获取用户")
    public User find (
           @ApiPathParam(name = "id", description = "用户ID") @PathVariable("id") Long id) {
        return userService.find(id);
    }

    @PutMapping("/{id}")
    @ApiMethod(summary = "通过ID修改用户", description = "通过ID修改用户")
    public Long update (
            @ApiPathParam(name = "id", description = "待修改用户ID") @PathVariable("id") Long id,
            @ApiBodyObject(clazz = User.class) @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ApiMethod(summary = "通过ID删除用户", description = "通过ID删除用户")
    public Long remove (
            @ApiPathParam(name = "id", description = "待删除用户ID") @PathVariable("id") Long id) {
        return userService.remove(id);
    }
}
