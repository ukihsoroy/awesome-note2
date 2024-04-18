package org.ko.api.rest;


import org.ko.api.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserRest {

    /**
     * 获取全部用户
     * @return
     */
    @GetMapping("/users")
    List<UserDTO> findAll ();

    /**
     * 获取通过ID查询用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    UserDTO findById (@PathVariable("id") Long id);

    /**
     * 新增用户
     * @param userDTO
     * @return
     */
    @PostMapping("/user")
    Long postUser (@RequestBody UserDTO userDTO);

    /**
     * 通过ID更新用户
     * @param userDTO
     * @return
     */
    @PutMapping("/user")
    Long updateUser (@RequestBody UserDTO userDTO);

    /**
     * 通过ID删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    Long removeUser (@PathVariable("id") Long id);
}
