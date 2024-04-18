package org.ko.web.service;


import org.ko.web.domain.User;
import org.ko.web.repository.UserCrudRepository;
import org.ko.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCrudRepository userCrudRepository;

    public int update (Integer id, String name) {
        return userRepository.updateById(id, name);
    }

    public void save(List<User> users) {
        userCrudRepository.save(users);
    }
}
