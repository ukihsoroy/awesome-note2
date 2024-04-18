package org.ko.web;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.ko.web.controller.HelloController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    private MockMvc mvc;

    @Before
    public void before () {
        mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }

}
