package com.imooc.thrift.user.dto;

/**
 * description: TeacherDTO <br>
 * date: 2020/3/1 18:00 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class TeacherDTO extends UserDTO {

    private String intro;

    private Integer stars;

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
