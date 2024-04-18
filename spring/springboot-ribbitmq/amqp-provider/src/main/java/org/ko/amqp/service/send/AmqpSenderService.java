package org.ko.amqp.service.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 发送消息到RabbitMQ
 * 维护Queue名称
 */
@Component
public class AmqpSenderService {

    private static final Logger _LOGGER = LoggerFactory.getLogger(AmqpSenderService.class);

    @Autowired private AmqpTemplate amqpTemplate;

    @Autowired private Queue queue;

    @Autowired private TopicExchange topicExchange;

    /**
     * 发送消息(默认队列)
     * @param message
     */
    public void send (String message) {
        _LOGGER.info("Send message: {}", message);
        amqpTemplate.convertAndSend("ko-queue", message);
    }

}
