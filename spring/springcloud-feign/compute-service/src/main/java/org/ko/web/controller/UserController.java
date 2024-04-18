package org.ko.web.controller;

import org.ko.api.dto.UserDTO;
import org.ko.api.rest.UserRest;
import org.ko.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController implements UserRest {

    @Autowired private UserService userService;

    /**
     * 获取全部用户
     * @return
     */
    @Override
    public List<UserDTO> findAll () {
        return userService.findAll();
    }

    /**
     * 获取通过ID查询用户
     * @param id
     * @return
     */
    @Override
    public UserDTO findById (@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @Override
    public Long postUser (@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    /**
     * 通过ID更新用户
     * @param userDTO
     * @return
     */
    @Override
    public Long updateUser (@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    /**
     * 通过ID删除用户
     * @param id
     * @return
     */
    @Override
    public Long removeUser (@PathVariable("id") Long id) {
        return userService.removeUser(id);
    }
}
