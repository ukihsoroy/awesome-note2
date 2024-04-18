package com.imooc.course.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.imooc.course.dto.CourseDTO;
import com.imooc.course.service.ICourseService;
import com.imooc.thrift.user.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * description: CourseController <br>
 * date: 2020/3/1 20:11 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@RestController
@RequestMapping("course")
public class CourseController {

    @Reference(version = "1.0.0")
    private ICourseService courseService;


    @GetMapping
    public List<CourseDTO> courseList (HttpServletRequest request) {
        UserDTO user = (UserDTO)request.getAttribute("user");
        System.out.println(user.toString());
        return courseService.courseList();
    }
}
