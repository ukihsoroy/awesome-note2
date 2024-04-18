package com.imooc.course.entity;

import java.io.Serializable;

/**
 * description: Course <br>
 * date: 2020/3/1 18:18 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class Course {

    private Integer id;
    private String title;
    private String description;
    private Integer teacherId;

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

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
}
