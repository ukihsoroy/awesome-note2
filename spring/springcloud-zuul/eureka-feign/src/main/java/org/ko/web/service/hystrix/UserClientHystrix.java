package org.ko.web.service.hystrix;

import org.ko.api.dto.UserDTO;
import org.ko.web.service.UserClientService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserClientHystrix implements UserClientService {

    @Override
    public List<UserDTO> findAll() {
        return new ArrayList<>();
    }

    @Override
    public UserDTO findById(Long id) {
        return new UserDTO();
    }

    @Override
    public Long postUser(UserDTO userDTO) {
        return 0L;
    }

    @Override
    public Long updateUser(UserDTO userDTO) {
        return 0L;
    }

    @Override
    public Long removeUser(Long id) {
        return 0L;
    }
}
