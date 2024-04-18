package org.ko.rpc.api.provider;

import org.ko.api.UserService;
import org.ko.dto.UserDTO;
import org.springframework.stereotype.Service;

/**
 * Spring管理的接口实例
 * 提供服务的具体实现
 */
@Service
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
