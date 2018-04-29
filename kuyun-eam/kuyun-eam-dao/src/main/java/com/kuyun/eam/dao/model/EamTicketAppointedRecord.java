package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;

public class EamTicketAppointedRecord extends BaseEntity implements Serializable {
    private Integer id;

    private Integer ticketId;

    /**
     * 接单人
     *
     * @mbg.generated
     */
    private Integer orderTakerId;

    /**
     * 委派备注，可为空
     *
     * @mbg.generated
     */
    private String appointCommont;

    /**
     * 接单/拒单
     *
     * @mbg.generated
     */
    private String action;

    /**
     * 拒单原因，可为空
     *
     * @mbg.generated
     */
    private String rejectCommont;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getOrderTakerId() {
        return orderTakerId;
    }

    public void setOrderTakerId(Integer orderTakerId) {
        this.orderTakerId = orderTakerId;
    }

    public String getAppointCommont() {
        return appointCommont;
    }

    public void setAppointCommont(String appointCommont) {
        this.appointCommont = appointCommont;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getRejectCommont() {
        return rejectCommont;
    }

    public void setRejectCommont(String rejectCommont) {
        this.rejectCommont = rejectCommont;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ticketId=").append(ticketId);
        sb.append(", orderTakerId=").append(orderTakerId);
        sb.append(", appointCommont=").append(appointCommont);
        sb.append(", action=").append(action);
        sb.append(", rejectCommont=").append(rejectCommont);
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
        EamTicketAppointedRecord other = (EamTicketAppointedRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getOrderTakerId() == null ? other.getOrderTakerId() == null : this.getOrderTakerId().equals(other.getOrderTakerId()))
            && (this.getAppointCommont() == null ? other.getAppointCommont() == null : this.getAppointCommont().equals(other.getAppointCommont()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getRejectCommont() == null ? other.getRejectCommont() == null : this.getRejectCommont().equals(other.getRejectCommont()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getOrderTakerId() == null) ? 0 : getOrderTakerId().hashCode());
        result = prime * result + ((getAppointCommont() == null) ? 0 : getAppointCommont().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getRejectCommont() == null) ? 0 : getRejectCommont().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        return result;
    }
}