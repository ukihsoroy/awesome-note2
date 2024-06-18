package com.imooc.course.dto;

import com.imooc.thrift.user.dto.TeacherDTO;

import java.io.Serializable;

/**
 * description: CourseDTO <br>
 * date: 2020/3/1 17:57 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class CourseDTO implements Serializable {

    private Integer id;
    private String title;
    private String description;
    private TeacherDTO teacher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }
}
