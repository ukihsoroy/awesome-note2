package org.ko.web.service;

import org.ko.api.dto.UserDTO;
import org.ko.web.bean.domain.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * User服务
 */
@Service
public class UserService {

    /**
     * 模拟数据库
     */
    private static Map<Long, User> data = new ConcurrentHashMap<>();

    /**
     * 查询全部用户
     * @return
     */
    public List<UserDTO> findAll() {
        Collection<User> users = data.values();
        List<UserDTO> userDTOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(users)) {
            userDTOS = users.stream().map(user -> {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(user, userDTO);
                return userDTO;
            }).collect(Collectors.toList());
        }
        return userDTOS;
    }

    /**
     * 通过ID查询用户
     * @param id
     * @return
     */
    public UserDTO findById (Long id) {
        User user = data.get(id);
        UserDTO userDTO = new UserDTO();
        if (user != null) {
            BeanUtils.copyProperties(user, userDTO);
        }
        return userDTO;
    }

    /**
     * 插入用户
     * @param userDTO
     * @return
     */
    public Long saveUser (UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        Long id = userDTO.getId() != null ? userDTO.getId() : data.size() + 1;
        user.setId(id);
        data.put(id, user);
        return id;
    }

    /**
     * 更新用户
     * @param userDTO
     * @return
     */
    public Long updateUser (UserDTO userDTO) {
        Long id = userDTO.getId();
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        data.put(id, user);
        return id;
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public Long removeUser (Long id) {
        data.remove(id);
        return id;
    }

}
