package org.ko.web.rest;

import org.ko.web.dto.UserDTO;
import org.ko.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired private UserService userService;

    /**
     * 处理GET请求, 获取全部用户数据
     * @return
     */
    @GetMapping
    public Collection<UserDTO> getAll () {
        return userService.getAll();
    }

    /**
     * 处理GET请求, 通过ID获取对应的User数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public UserDTO get (@PathVariable("id") Long id) {
        return userService.get(id);
    }

    /**
     * 处理POST请求, 保存User数据
     * @param user
     * @return
     */
    @PostMapping
    public UserDTO post (@ModelAttribute UserDTO user) {
        return userService.post(user);
    }

    /**
     * 处理PUT请求, 通过ID修改User数据
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/{id}")
    public UserDTO put (@PathVariable("id") Long id, @ModelAttribute UserDTO user) {
        return userService.put(id, user);
    }

    /**
     * 处理DELETE请求, 通过ID删除用户数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public UserDTO remove (@PathVariable("id") Long id) {
        return userService.remove(id);
    }


}
