package org.ko.nacos.discovery.consumer.client;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.ko.nacos.discovery.consumer.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@Api(tags = "使用RestTemplate")
@RequestMapping("rest/user")
public class RestTemplateClient {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    @ApiOperation("获取用户列表")
    public List<User> queryUserList() {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/user", serviceInstance.getHost(), serviceInstance.getPort());
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(url, User[].class)));
    }

    @GetMapping("{name}")
    @ApiOperation("通过用户名称获取用户")
    public User queryUserByName(@PathVariable String name) {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/user/%s", serviceInstance.getHost(), serviceInstance.getPort(), name);
        return restTemplate.getForObject(url, User.class);
    }

    @PostMapping
    @ApiOperation("新增用户数据")
    public String insertUser(@RequestBody User user) {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/user", serviceInstance.getHost(), serviceInstance.getPort());
        return restTemplate.postForObject(url, user, String.class);
    }

    @PutMapping("{name}")
    @ApiOperation("新增一条用户数据")
    public String updateUser(@PathVariable String name, @RequestBody User user) {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/user/%s", serviceInstance.getHost(), serviceInstance.getPort(), name);
        restTemplate.put(url, user);
        return "SUCCESS";
    }

    @DeleteMapping("{name}")
    @ApiOperation("通过用户名称删除一条数据")
    public String removeUser(@PathVariable String name) {
        //使用 LoadBalanceClient 和 RestTemplate 结合的方式来访问
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/user/%s", serviceInstance.getHost(), serviceInstance.getPort(), name);
        restTemplate.delete(url);
        return "SUCCESS";
    }

}
