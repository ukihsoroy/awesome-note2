package org.ko.dashboard.domain;

import java.io.Serializable;

/**
 * t_day_video_access_topn_stat
 * @author 
 */
public class DayVideoAccessTopnStat extends DayVideoAccessTopnStatKey implements Serializable {
    /**
     * 访问次数
     */
    private Long times;

    private static final long serialVersionUID = 1L;

    public Long getTimes() {
        return times;
    }

    public void setTimes(Long times) {
        this.times = times;
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
        DayVideoAccessTopnStat other = (DayVideoAccessTopnStat) that;
        return (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getCmsId() == null ? other.getCmsId() == null : this.getCmsId().equals(other.getCmsId()))
            && (this.getTimes() == null ? other.getTimes() == null : this.getTimes().equals(other.getTimes()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getCmsId() == null) ? 0 : getCmsId().hashCode());
        result = prime * result + ((getTimes() == null) ? 0 : getTimes().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", times=").append(times);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}