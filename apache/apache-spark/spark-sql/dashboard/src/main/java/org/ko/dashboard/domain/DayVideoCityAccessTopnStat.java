package org.ko.dashboard.domain;

import java.io.Serializable;

/**
 * t_day_video_city_access_topn_stat
 * @author 
 */
public class DayVideoCityAccessTopnStat extends DayVideoCityAccessTopnStatKey implements Serializable {
    /**
     * 点击次数
     */
    private Long times;

    /**
     * 排名
     */
    private Short timesRank;

    private static final long serialVersionUID = 1L;

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
    }

    public Short getTimesRank() {
        return timesRank;
    }

    public void setTimesRank(Short timesRank) {
        this.timesRank = timesRank;
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
        DayVideoCityAccessTopnStat other = (DayVideoCityAccessTopnStat) that;
        return (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getCmsId() == null ? other.getCmsId() == null : this.getCmsId().equals(other.getCmsId()))
            && (this.getCity() == null ? other.getCity() == null : this.getCity().equals(other.getCity()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()))
            && (this.getTimesRank() == null ? other.getTimesRank() == null : this.getTimesRank().equals(other.getTimesRank()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getCmsId() == null) ? 0 : getCmsId().hashCode());
        result = prime * result + ((getCity() == null) ? 0 : getCity().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        result = prime * result + ((getTimesRank() == null) ? 0 : getTimesRank().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", times=").append(times);
        sb.append(", timesRank=").append(timesRank);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}