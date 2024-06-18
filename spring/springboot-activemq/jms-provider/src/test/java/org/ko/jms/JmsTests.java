package org.ko.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.jms.send.JmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JmsTests {

    @Autowired
    JmsSender jmsSender;

    @Test
    public void sendTest () {
        for (int i = 1; i < 100; i++) {
            jmsSender.send("message" + i);
        }
    }

}
