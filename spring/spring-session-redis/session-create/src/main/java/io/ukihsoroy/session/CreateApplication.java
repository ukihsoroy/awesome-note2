package io.ukihsoroy.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * description: CreateApplication <br>
 * date: 2020/11/21 23:53 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@SpringBootApplication
@RestController
public class CreateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreateApplication.class);
    }

    @RequestMapping("/add/{name}/{value}")
    public String addSession(HttpServletRequest request, @PathVariable("name") String name, @PathVariable("value") String value){
        HttpSession session = request.getSession();
        session.setAttribute(name,value);
        return "sessionId:"+session.getId()+" name:"+name;
    }

}
