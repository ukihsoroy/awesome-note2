package io.ukihsoroy.robot.event;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p></p>
 *
 * @author K.O
 * @email ko.shen@hotmail.com
 */
public abstract class AbstractEvent<T> implements IEvent<T> {

    @Autowired protected ObjectMapper mapper;

    @Override
    public T parse(String request) throws IOException {
        if (StringUtils.isEmpty(request)) {
            return null;
        }
        return mapper.readValue(request, new TypeReference<T>(){});
    }
}
