package org.ko.jms.send;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class JmsSender {

    private static final Logger _LOGGER = LoggerFactory.getLogger(JmsSender.class);

    @Autowired private JmsTemplate jmsTemplate;

    @Autowired private Queue queue;

    @Autowired private Topic topic;

    public void send (String message) {
        _LOGGER.info("Send message: {}", message);
        jmsTemplate.convertAndSend(this.queue, message);
        jmsTemplate.convertAndSend(this.topic, message);
    }
}
