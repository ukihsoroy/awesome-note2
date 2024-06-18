package org.ko.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//开启配置刷新
@RefreshScope
//Rest
@RestController
//全局映射
@RequestMapping("services")
public class ServicesController {

    //获取服务相关信息
    @Autowired private DiscoveryClient client;

    //读取配置文件name
    @Value("${name}") private String name;

    //读取配置文件age
    @Value("${age}") private Short age;

    @GetMapping
    public String getServices() {
        String desc = client.description();
        return desc;
    }

    @GetMapping("data")
    public String getData () {
        return String.format("name: %s, age: %d", name, age);
    }

}
