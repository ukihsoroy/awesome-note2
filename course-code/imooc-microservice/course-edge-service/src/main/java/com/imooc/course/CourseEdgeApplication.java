package com.imooc.course;

import com.imooc.course.filter.CourseFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * description: UserEdgeApplication <br>
 * date: 2020/2/29 19:37 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@SpringBootApplication
public class CourseEdgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseEdgeApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        CourseFilter courseFilter = new CourseFilter();
        filterRegistrationBean.setFilter(courseFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        return filterRegistrationBean;
    }

}
