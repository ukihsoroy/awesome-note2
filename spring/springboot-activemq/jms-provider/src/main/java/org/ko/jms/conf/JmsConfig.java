package org.ko.jms.conf;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

@Configuration
public class JmsConfig {

    /**
     * 队列模式
     * @return
     */
    @Bean
    public Queue queue () {
        return new ActiveMQQueue("jms-queue");
    }

    /**
     * 订阅模式
     * @return
     */
    @Bean
    public Topic topic () {
        return new ActiveMQTopic("jms-topic");
    }

}
