package org.ko.web.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

//model类型注释
@ApiModel
public class User {

    //类属性注释
    @ApiModelProperty(value = "id", name = "主键ID")
    private Long id;

    @ApiModelProperty(value = "name", name = "姓名")
    private String name;

    @ApiModelProperty(value = "age", name = "年龄")
    private Short age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }
}
