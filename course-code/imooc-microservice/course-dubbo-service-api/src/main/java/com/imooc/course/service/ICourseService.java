package com.imooc.course.service;

import com.imooc.course.dto.CourseDTO;

import java.util.List;

/**
 * description: ICourseService <br>
 * date: 2020/3/1 17:56 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public interface ICourseService {

    List<CourseDTO> courseList();
}
