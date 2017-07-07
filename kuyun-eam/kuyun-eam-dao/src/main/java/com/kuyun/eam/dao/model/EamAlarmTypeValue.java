package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class EamAlarmTypeValue implements Serializable {
    private Integer id;

    private Integer alarmId;

    private BigDecimal upperBound;

    private BigDecimal lowerBound;

    private BigDecimal duration;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", alarmId=").append(alarmId);
        sb.append(", upperBound=").append(upperBound);
        sb.append(", lowerBound=").append(lowerBound);
        sb.append(", duration=").append(duration);
        sb.append("]");
        return sb.toString();
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
        EamAlarmTypeValue other = (EamAlarmTypeValue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getUpperBound() == null ? other.getUpperBound() == null : this.getUpperBound().equals(other.getUpperBound()))
            && (this.getLowerBound() == null ? other.getLowerBound() == null : this.getLowerBound().equals(other.getLowerBound()))
            && (this.getDuration() == null ? other.getDuration() == null : this.getDuration().equals(other.getDuration()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getUpperBound() == null) ? 0 : getUpperBound().hashCode());
        result = prime * result + ((getLowerBound() == null) ? 0 : getLowerBound().hashCode());
        result = prime * result + ((getDuration() == null) ? 0 : getDuration().hashCode());
        return result;
    }
}