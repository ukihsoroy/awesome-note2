package org.ko.oauth.service;


import org.ko.oauth.domain.UserEntity;

public interface IUserService {

    UserEntity findByName(String username);
}
