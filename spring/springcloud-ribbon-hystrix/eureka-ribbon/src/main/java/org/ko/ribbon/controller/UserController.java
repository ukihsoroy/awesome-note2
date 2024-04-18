package org.ko.ribbon.controller;

import org.ko.api.dto.UserDTO;
import org.ko.ribbon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//REST
@RestController
public class UserController {

    @Autowired private UserService userService;

    /**
     * 获取全部用户
     * @return
     */
    @GetMapping("/users")
    public List<UserDTO> findAll () {
        return userService.findAll();
    }

    /**
     * 通过ID获取用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public UserDTO findById (@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    /**
     * 插入用户
     * @param userDTO
     * @return
     */
    @PostMapping("/user")
    public Long postUser (@RequestBody UserDTO userDTO) {
        return userService.postUser(userDTO);
    }

    /**
     * 修改用户
     * @param userDTO
     * @return
     */
    @PutMapping("/user")
    public Long updateUser (@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    /**
     * 通过ID删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public Long removeUser (@PathVariable("id") Long id) {
        return userService.removeUser(id);
    }
}
