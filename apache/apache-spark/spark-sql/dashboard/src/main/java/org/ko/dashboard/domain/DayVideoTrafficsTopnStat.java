package org.ko.dashboard.domain;

import java.io.Serializable;

/**
 * t_day_video_traffics_topn_stat
 * @author 
 */
public class DayVideoTrafficsTopnStat extends DayVideoTrafficsTopnStatKey implements Serializable {
    private Long traffics;

    private static final long serialVersionUID = 1L;

    public Long getTraffics() {
        return traffics;
    }

    public void setTraffics(Long traffics) {
        this.traffics = traffics;
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
        DayVideoTrafficsTopnStat other = (DayVideoTrafficsTopnStat) that;
        return (this.getDay() == null ? other.getDay() == null : this.getDay().equals(other.getDay()))
            && (this.getCmsId() == null ? other.getCmsId() == null : this.getCmsId().equals(other.getCmsId()))
            && (this.getTraffics() == null ? other.getTraffics() == null : this.getTraffics().equals(other.getTraffics()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDay() == null) ? 0 : getDay().hashCode());
        result = prime * result + ((getCmsId() == null) ? 0 : getCmsId().hashCode());
        result = prime * result + ((getTraffics() == null) ? 0 : getTraffics().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", traffics=").append(traffics);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}