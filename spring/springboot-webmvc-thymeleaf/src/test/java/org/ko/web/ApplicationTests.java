package org.ko.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.controller.HelloController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    private MockMvc mvc;

    @Before
    public void before () {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }


}
