package io.ukihsoroy.robot.application;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.ukihsoroy.robot.event.IEvent;
import io.ukihsoroy.robot.event.IEventMessage;

/**
 * <p></p>
 *
 * @author K.O
 * @email ko.shen@hotmail.com
 */
@RestController
public class EventService {

    @Autowired private Map<String, IEvent<IEventMessage>> events;

    @PostMapping
    public String event(@RequestBody String request) {
        System.out.println(request);
        return "1";
    }

}
