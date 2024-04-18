package org.ko.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.ko.web.base.Render;

public class JacksonTest {

    @Test
    public void test1 () throws JsonProcessingException {
        Render<String> render = new Render<>();
        render.setData("This is bad!");
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(render);
        System.out.println(result);
    }
}
