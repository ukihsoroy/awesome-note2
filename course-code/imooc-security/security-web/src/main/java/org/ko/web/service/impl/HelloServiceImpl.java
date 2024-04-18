package org.ko.web.service.impl;

import org.ko.web.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public void hello(String name) {
        System.out.println(name);
    }
}
