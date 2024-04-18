package org.ko.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.data.cluster.dao.DogMapper;
import org.ko.data.cluster.domain.Dog;
import org.ko.data.master.dao.UserMapper;
import org.ko.data.master.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DSTests {

    @Autowired private UserMapper userMapper;

    @Autowired private DogMapper dogMapper;

    @Test
    public void userMapperTest () {
        User user = userMapper.findById(1);
        System.out.println(user.getName());
    }

    @Test
    public void dogMapperTest () {
        Dog dog = dogMapper.findById(1);
        System.out.println(dog.getName());
    }

}
