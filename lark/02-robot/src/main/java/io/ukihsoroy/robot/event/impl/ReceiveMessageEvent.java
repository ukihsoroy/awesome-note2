package io.ukihsoroy.robot.event.impl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import io.ukihsoroy.robot.entity.ReceiveMessage;
import io.ukihsoroy.robot.event.AbstractEvent;

/**
 * <p></p>
 *
 * @author K.O
 * @email ko.shen@hotmail.com
 */
@Component("im.message.receive_v1")
public class ReceiveMessageEvent extends AbstractEvent<ReceiveMessage> {
    @Override
    public void executor(String request) throws IOException {
        ReceiveMessage message = parse(request);
        System.out.println(message);
    }
}
