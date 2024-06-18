package org.ko.security.service.impl;

import org.ko.security.constant.RoleConstant;
import org.ko.security.entity.UserEntity;
import org.ko.security.mapper.UserMapper;
import org.ko.security.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class BaseUserService implements UserService {

    private final UserMapper userMapper;

    public BaseUserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public boolean insert(UserEntity userEntity) {
        String username = userEntity.getUsername();
        if (exist(username))
            return false;
        userEntity.setRoles(RoleConstant.ROLE_USER);
        int result = userMapper.insert(userEntity);
        return  result == 1;
    }

    @Override
    public UserEntity getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 判断用户是否存在
     * @param username 账号
     * @return 密码
     */
    private boolean exist(String username){
        UserEntity userEntity = userMapper.selectByUsername(username);
        return (userEntity != null);
    }

}
