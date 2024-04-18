package org.ko.json.jackson;

import java.util.Date;

public class City {

    /**
     * 主键
     */
    private Long id;

    /**
     * 城市全路径
     */
    private String cityPath;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 缩写
     */
    private String shorten;

    /**
     * 创建时间
     */
    private Date createDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityPath() {
        return cityPath;
    }

    public void setCityPath(String cityPath) {
        this.cityPath = cityPath;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getShorten() {
        return shorten;
    }

    public void setShorten(String shorten) {
        this.shorten = shorten;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
