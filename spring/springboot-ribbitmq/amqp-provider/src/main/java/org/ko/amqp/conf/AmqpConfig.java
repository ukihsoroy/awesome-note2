package org.ko.amqp.conf;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义消息队列协议
 */
@Configuration
public class AmqpConfig {

    @Bean
    public Queue queue () {
        Map<String, Object> args = new HashMap<>();
// // set the queue with a dead letter feature
        args.put("x-queue-type", "classic");
        return new Queue("ko-queue", true, false, false, args);
    }

    @Bean
    public TopicExchange topicExchange () {
        return new TopicExchange("ko-topic");
    }
}
