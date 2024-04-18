package org.ko.mvc.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    public User(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
