package com.imooc.course.filter;

import com.imooc.thrift.user.dto.UserDTO;
import com.imooc.user.client.LoginFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: CourseFilter <br>
 * date: 2020/3/1 20:15 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class CourseFilter extends LoginFilter {
    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }
}
