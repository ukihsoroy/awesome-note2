package org.ko.api;

import org.ko.dto.UserDTO;

/**
 * 通用接口, 由服务提供和消费共通依赖
 */
public interface UserService {

    UserDTO getUser();
}
