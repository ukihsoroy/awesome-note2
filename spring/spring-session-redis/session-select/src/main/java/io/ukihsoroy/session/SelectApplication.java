package io.ukihsoroy.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * description: SelectApplication <br>
 * date: 2020/11/21 23:52 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@SpringBootApplication
@RestController
public class SelectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SelectApplication.class);
    }

    @RequestMapping("/get/{name}")
    public String getSession(HttpServletRequest request, @PathVariable("name") String name){
        HttpSession session = request.getSession();
        String value = (String)session.getAttribute(name);
        return "sessionId:"+session.getId()+" value:"+value;
    }

}
