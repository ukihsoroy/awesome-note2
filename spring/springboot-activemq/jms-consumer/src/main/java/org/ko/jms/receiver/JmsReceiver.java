package org.ko.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsReceiver {

    private static final Logger _Logger = LoggerFactory.getLogger(JmsReceiver.class);

    @JmsListener(destination = "jms-queue", containerFactory = "jmsListenerContainerQueue")
    public void queueProcess (String message) {
        _Logger.info("Receiver queue message: {}", message);
    }


    @JmsListener(destination = "jms-topic", containerFactory = "jmsListenerContainerTopic")
    public void topicProcess (String message) {
        _Logger.info("Receiver topic message: {}", message);
    }
}
