package org.ko.rpc.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.ko.api.UserService;
import org.ko.dto.UserDTO;
import org.springframework.stereotype.Service;

//Spring 注解
@Service
public class ConsumerService {
    /**
     * url: 连接的url
     * version: 接口版本
     */
    @Reference(url = "dubbo://localhost:20880/org.ko.api.UserService", version = "1.0.0")
    private UserService userService;

    public UserDTO getUser() {
        return userService.getUser();
    }

}
