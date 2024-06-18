package org.ko.web.service;

import org.ko.api.rest.UserRest;
import org.ko.web.service.hystrix.UserClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 开启Feign客户端
 * #{@link FeignClient}
 * value 服务名称
 * fallback Hystrix 熔断后降级处理
 * configuration Feign客户端初始化 不配置使用默认
 */
@FeignClient(value = "user", fallback = UserClientHystrix.class)
public interface UserClientService extends UserRest {

}
