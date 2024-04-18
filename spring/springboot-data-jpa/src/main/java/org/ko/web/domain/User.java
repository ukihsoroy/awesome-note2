package org.ko.web.domain;

import javax.persistence.*;

/**
 * 用户： 先开发实体类==》自动生成数据表
 * 注意引用的包
 * @see javax.persistence
 */
@Entity
@Table  //(name = "boss")-->可以指定name映射其他表
public class User {


    @Id             //主键
    @GeneratedValue //自增
    private Integer id;

    /**
     * Column 注解可以做一些字段的限时
     * 字段名字，字段长度， 是否允许为空等
     */
    @Column(length = 20)
    private String name;

    private Integer age;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
