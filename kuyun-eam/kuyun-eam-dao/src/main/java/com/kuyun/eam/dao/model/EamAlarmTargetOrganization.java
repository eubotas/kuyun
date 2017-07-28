package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamAlarmTargetOrganization extends BaseEntity implements Serializable {
    private Integer id;

    private Integer alarmId;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", alarmId=").append(alarmId);
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
        EamAlarmTargetOrganization other = (EamAlarmTargetOrganization) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAlarmId() == null ? other.getAlarmId() == null : this.getAlarmId().equals(other.getAlarmId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAlarmId() == null) ? 0 : getAlarmId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        return result;
    }
}