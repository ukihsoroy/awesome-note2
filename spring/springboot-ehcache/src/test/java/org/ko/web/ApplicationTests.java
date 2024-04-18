package org.ko.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.domain.User;
import org.ko.web.repository.UserJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.load;

//@SpringBootTest
//@RunWith(SpringRunner.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

    private static final Logger _LOGGER = LoggerFactory.getLogger(ApplicationTests.class);

    @Autowired private UserJpaRepository userRepository;


    @Autowired
    private CacheManager cacheManager;

    @Before
    public void before () {
        User user = new User();
        user.setName("K.O");
        user.setAge(18);
        userRepository.save(user);
    }


    @Test
    public void test() throws Exception {
        long s1 = currentTimeMillis();
        userRepository.findByName("K.O");
        long e1 = currentTimeMillis();
        _LOGGER.info("First select time: {}ms", (e1 - s1));

        long s2 = currentTimeMillis();
        userRepository.findByName("K.O");
        long e2 = currentTimeMillis();
        _LOGGER.info("Second select time: {}ms", (e2 - s2));

        _LOGGER.info("quick: {}ms", (e1 + e2 - s1 - s2));

    }

}
