package org.ko.dashboard.domain;

import java.io.Serializable;

/**
 * t_day_video_traffics_topn_stat
 * @author 
 */
public class DayVideoTrafficsTopnStatKey implements Serializable {
    private String day;

    private Long cmsId;

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
        DayVideoTrafficsTopnStatKey other = (DayVideoTrafficsTopnStatKey) that;
        return (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getCmsId() == null ? other.getCmsId() == null : this.getCmsId().equals(other.getCmsId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getCmsId() == null) ? 0 : getCmsId().hashCode());
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}