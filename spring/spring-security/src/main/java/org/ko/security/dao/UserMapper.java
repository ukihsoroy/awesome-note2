package org.ko.security.dao;

import org.ko.security.bean.User;

import java.util.List;

public interface UserMapper {

    List<User> gerUsers ();

    User gerUser(String username);
}
