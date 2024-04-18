package org.ko.dashboard.domain;

import java.io.Serializable;

/**
 * t_day_video_city_access_topn_stat
 * @author 
 */
public class DayVideoCityAccessTopnStatKey implements Serializable {
    /**
     * log生成日期
     */
    private String day;

    /**
     * 课程ID
     */
    private Long cmsId;

    /**
     * 点击城市
     */
    private String city;

    private static final long serialVersionUID = 1L;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getCmsId() {
        return cmsId;
    }

    public void setCmsId(Long cmsId) {
        this.cmsId = cmsId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DayVideoCityAccessTopnStatKey other = (DayVideoCityAccessTopnStatKey) that;
        return (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getCmsId() == null ? other.getCmsId() == null : this.getCmsId().equals(other.getCmsId()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getCmsId() == null) ? 0 : getCmsId().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", day=").append(day);
        sb.append(", cmsId=").append(cmsId);
        sb.append(", city=").append(city);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}