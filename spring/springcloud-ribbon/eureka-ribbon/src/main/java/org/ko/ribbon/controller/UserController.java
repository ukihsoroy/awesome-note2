package org.ko.ribbon.controller;

import org.ko.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

//REST
@RestController
public class UserController {

    /**
     * 对应发布的服务端的spring.application.name-服务名
     * Eureka服务名---域名映射
     */
    private static final String URL_PREFIX = "http://USER/";

    /**
     * 获取连接
     */
    @Autowired private RestTemplate client;

    /**
     * RestTemplate发起GET请求-获取全部用户
     * @return
     */
    @GetMapping("/users")
    public List<UserDTO> findAll () {
        /**
         * @Param url 发起请求链接
         * @Param class ResponseEntity返回值类型
         */
        return Arrays.asList(client.getForEntity(URL_PREFIX + "users", UserDTO[].class).getBody());
    }

    /**
     * RestTemplate发起GET请求-通过ID获取用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public UserDTO findById (@PathVariable("id") Long id) {
        /**
         * @Param url 发起请求链接
         * @Patam class ResponseEntity返回值类型
         * @Param Object Get请求参数-对应{1}占位符
         */
        return client.getForEntity(URL_PREFIX + "user/{1}", UserDTO.class, id).getBody();
    }

    /**
     * RestTemplate发起POST请求-插入用户
     * @param userDTO
     * @return
     */
    @PostMapping("/user")
    public Long postUser (@RequestBody UserDTO userDTO) {
        /**
         * @Param url 发起请求链接
         * @Param Object Post请求参数-对应{1}占位符
         * @Patam class ResponseEntity返回值类型
         */
        return client.postForEntity(URL_PREFIX + "user", userDTO, Long.class).getBody();
    }

    /**
     * RestTemplate发起PUT请求-修改用户
     * @param userDTO
     * @return
     */
    @PutMapping("/user")
    public Long updateUser (@RequestBody UserDTO userDTO) {
        /**
         * @Param url 发起请求链接
         * @Param Object Put请求参数
         * @void RestTemplate PUT没有返回值 不需要Class
         */
        client.put(URL_PREFIX + "user", userDTO);
        return 1L;
    }

    /**
     * RestTemplate发起DELETE请求-通过ID删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public Long removeUser (@PathVariable("id") Long id) {
        /**
         * @Param url 发起请求链接
         * @Param Object Delete请求参数-对应{1}占位符
         * @void RestTemplate DELETE没有返回值 不需要Class
         */
        client.delete(URL_PREFIX + "user/{1}", id);
        return 1L;
    }
}
