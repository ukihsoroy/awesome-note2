import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.ribbon.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTests {

    @Autowired private MockMvc mockMvc;

    @Before
    public void before () {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test
    public void test1 () throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap();

//        mockMvc.perform(
//                post("/user")
//                        .accept(MediaType.APPLICATION_JSON).
//                .params(params)
//        );
    }
}
