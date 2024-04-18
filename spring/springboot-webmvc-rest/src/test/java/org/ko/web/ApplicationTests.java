package org.ko.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.rest.HelloController;
import org.ko.web.rest.UserController;
import org.ko.web.service.UserService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    private MockMvc mvc;

    @Before
    public void before () {
        mvc = MockMvcBuilders.standaloneSetup(
                new HelloController(),
                new UserController(),
                new UserService()
        ).build();
    }

    @Test
    public void testHello() throws Exception {
        mvc.perform(get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World!")));
    }

    @Test
    public void testUser () throws Exception {

//        RequestBuilder request = post("/user")
//                .param("id", "1")
//                .param("name", "K.O")
//                .param("gender", "man")
//                .param("sing", "sit hill watch tiger fight!");

        RequestBuilder request = get("/user");
        //1. post user 插入数据
        mvc.perform(request)
                .andExpect(status().isOk());

        //2. get user 查全部列表-应该为空
//        mvc.perform(request)
//                .andExpect(status().isOk());
//                .andExpect(content().string(equalTo("[]")));

        //2. post user 添加


    }


}
