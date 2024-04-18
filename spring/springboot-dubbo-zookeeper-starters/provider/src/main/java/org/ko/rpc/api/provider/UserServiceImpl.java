package org.ko.rpc.api.provider;

import com.alibaba.dubbo.config.annotation.Service;
import org.ko.api.UserService;
import org.ko.dto.UserDTO;

/**
 * 提供服务的具体实现-注解为Dubbo自定注解
 * com.alibaba.dubbo.config.annotation.Service;
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {


    @Override
    public UserDTO getUser() {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setName("K.O");
        user.setAge((short)27);
        return user;
    }
}
