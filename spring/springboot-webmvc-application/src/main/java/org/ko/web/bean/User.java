package org.ko.web.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * springboot 配置文件中封装了random对象
 */
@Component
public class User {

    @Value("${user.name}")
    private String name;

    @Value("${user.gender}")
    private String gender;

    @Value("${user.blog}")
    private String blog;

    @Value("${user.hobby}")
    private String hobby;

    @Value("${random.int}")
    private Integer number;

    @Value("${random.value}")
    private String value;

    @Value("${random.long}")
    private Long bigInt;

    @Value("${random.int(10)}")
    private Integer t1;

    @Value("${random.int[10,20]}")
    private Integer t2;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBlog() {
        return blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getBigInt() {
        return bigInt;
    }

    public void setBigInt(Long bigInt) {
        this.bigInt = bigInt;
    }

    public Integer getT1() {
        return t1;
    }

    public void setT1(Integer t1) {
        this.t1 = t1;
    }

    public Integer getT2() {
        return t2;
    }

    public void setT2(Integer t2) {
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", blog='" + blog + '\'' +
                ", hobby='" + hobby + '\'' +
                ", number=" + number +
                ", value='" + value + '\'' +
                ", bigInt=" + bigInt +
                ", t1=" + t1 +
                ", t2=" + t2 +
                '}';
    }
}
