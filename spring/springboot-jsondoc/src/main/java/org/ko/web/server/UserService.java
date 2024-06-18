package org.ko.web.server;

import org.ko.web.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private static Map<Long, User> data = new ConcurrentHashMap<>();

    public Collection<User> getAll() {
        return data.values();
    }

    public Long save(User user) {
        data.put(data.size() + 1L, user);
        return 1L;
    }

    public User find (Long id) {
        return data.get(id);
    }

    public Long update (Long id, User user) {
        data.put(id, user);
        return 1L;
    }

    public Long remove (Long id) {
        data.remove(id);
        return 1L;
    }

}
