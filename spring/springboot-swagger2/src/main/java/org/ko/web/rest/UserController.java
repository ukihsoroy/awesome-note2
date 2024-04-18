package org.ko.web.rest;

import io.swagger.annotations.*;
import org.ko.web.entity.User;
import org.ko.web.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/user")
//配置Swagger 接口组注释
@Api(value = "UserController", description = "用户集成接口")
public class UserController {

    @Autowired private UserService userService;

    @GetMapping
    //单个方法注释
    @ApiOperation("获取全部用户")
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @PostMapping
    @ApiOperation("新增用户")
    public Long save(
            //参数注释
            @ApiParam(name = "user", value = "用户数据", required = true) @RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询用户")
    @ApiResponses({
            @ApiResponse(code = 400, message = "User doesn't exist")
    })
    public User find (
            @ApiParam(name = "id", value = "主键", required = true) @PathVariable("id") Long id) {
        return userService.find(id);
    }

    @PutMapping("/{id}")
    @ApiOperation("通过ID修改用户")
    public Long update (
            @ApiParam(name = "id", value = "主键", required = true) @PathVariable("id") Long id,
            @ApiParam(name = "user", value = "用户数据", required = true) @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "Long", paramType = "path")
    })
    public Long remove (@PathVariable("id") Long id) {
        return userService.remove(id);
    }
}
