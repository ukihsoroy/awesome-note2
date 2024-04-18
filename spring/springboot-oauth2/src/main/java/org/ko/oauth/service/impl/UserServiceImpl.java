package org.ko.oauth.service.impl;

import org.ko.oauth.repository.UserEntityRepository;
import org.ko.oauth.domain.UserEntity;
import org.ko.oauth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Throwable.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserEntity findByName(String username) {
        return userEntityRepository.findByUsername(username) ;
    }
}
