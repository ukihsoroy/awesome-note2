package org.ko.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.amqp.service.send.AmqpSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AmqpTests {

    @Autowired
    AmqpSenderService amqpSenderService;

    @Test
    public void sendTest () {
        for (int i = 1; i < 100; i++) {
            amqpSenderService.send("中文测试" + i);
        }
    }

}
