package org.ko.web.service;

import org.ko.web.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    /**
     * 创建线程安全的map 模拟DB
     * volatile 保证线程可见性
     */
    private static volatile Map<Long, UserDTO> table = new ConcurrentHashMap<>();

    public Collection<UserDTO> getAll() {
        return table.values();
    }

    public UserDTO get(Long id) {
        return table.get(id);
    }

    public UserDTO post(UserDTO user) {
        user.setId((long)(table.size() + 1));
        return table.put((long)(table.size() + 1), user);
    }

    public UserDTO put(Long id, UserDTO user) {
        return table.put(id, user);
    }

    public UserDTO remove(Long id) {
        return table.remove(id);
    }

}
