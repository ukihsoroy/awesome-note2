package org.ko.greenplum.entity.vo;

/**
 * 详细地址
 */
public class Address {

    public Address(String country, String province, String city, String area, String info) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.area = area;
        this.info = info;
    }

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 市区/乡镇
     */
    private String area;

    /**
     * 详细地址
     */
    private String info;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
