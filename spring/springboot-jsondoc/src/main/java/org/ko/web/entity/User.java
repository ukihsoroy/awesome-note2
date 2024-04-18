package org.ko.web.entity;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

//model类型注释
@ApiObject(name = "User", description = "用户实体类")
public class User {

    //类属性注释
    @ApiObjectField(name = "id", description = "用户主键ID")
    private Long id;

    @ApiObjectField(name = "name", description = "用户姓名")
    private String name;

    @ApiObjectField(name = "age", description = "用户年龄")
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
