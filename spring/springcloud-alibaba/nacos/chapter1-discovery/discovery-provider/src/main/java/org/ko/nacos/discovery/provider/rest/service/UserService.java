package org.ko.nacos.discovery.provider.rest.service;


import org.ko.nacos.discovery.provider.rest.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private static Map<String, User> USER_TABLE = new ConcurrentHashMap<>();

    /**
     * 查询列表
     */
    public List<User> queryUserList() {
        return new ArrayList<>(USER_TABLE.values());
    }

    /**
     * 通过名字查询用户
     * @param name
     * @return
     */
    public User queryUserByName (String name) {
        return USER_TABLE.get(name);
    }

    /**
     * 插入用户
     * @param user
     * @return
     */
    public String insertUser(User user) {
        USER_TABLE.put(user.getName(), user);
        return user.getName();
    }

    /**
     * 更新用户
     * @param name
     * @param user
     * @return
     */
    public String updateUser(String name, User user) {
        USER_TABLE.put(name, user);
        return user.getName();
    }

    /**
     * 删除用户
     * @param name
     * @return
     */
    public String removeUser (String name) {
        USER_TABLE.remove(name);
        return name;
    }
}
