package org.ko.rpc.api.provider;

import org.ko.rpc.api.UserService;
import org.springframework.stereotype.Service;

/**
 * Spring管理的接口实例
 * 提供服务的具体实现
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserName() {
        return "K.O";
    }
}
