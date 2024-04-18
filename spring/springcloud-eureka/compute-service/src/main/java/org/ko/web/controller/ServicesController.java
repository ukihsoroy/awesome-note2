package org.ko.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("services")
public class ServicesController {

    //获取服务相关信息
    @Autowired private DiscoveryClient client;

    @GetMapping
    public String getServices() {
        String desc = client.description();
        return desc;
    }

}
