package com.kuyun.eam.dao.model;

import java.io.Serializable;
import java.util.Date;

public class EamTicket implements Serializable {
    private Integer ticketId;

    private Integer ticketTypeId;

    /**
     * 描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 上传图片
     *
     * @mbg.generated
     */
    private String imagePath1;

    /**
     * 上传图片
     *
     * @mbg.generated
     */
    private String imagePath2;

    /**
     * 上传图片
     *
     * @mbg.generated
     */
    private String imagePath3;

    /**
     * 上传图片
     *
     * @mbg.generated
     */
    private String imagePath4;

    /**
     * 优先级 （一般，紧急, 非常紧急）
     *
     * @mbg.generated
     */
    private String priority;

    /**
     * 处理人
     *
     * @mbg.generated
     */
    private Integer executorId;

    /**
     * 状态（未解决, 处理中, 已解决, 不需处理）
     *
     * @mbg.generated
     */
    private String status;

    private Date endDate;

    private Integer createUserId;

    private Date createTime;

    private Integer updateUserId;

    private Date updateTime;

    private Boolean deleteFlag;

    private Integer organizationId;

    private static final long serialVersionUID = 1L;

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(Integer ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public String getImagePath3() {
        return imagePath3;
    }

    public void setImagePath3(String imagePath3) {
        this.imagePath3 = imagePath3;
    }

    public String getImagePath4() {
        return imagePath4;
    }

    public void setImagePath4(String imagePath4) {
        this.imagePath4 = imagePath4;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Integer getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Integer executorId) {
        this.executorId = executorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ticketId=").append(ticketId);
        sb.append(", ticketTypeId=").append(ticketTypeId);
        sb.append(", description=").append(description);
        sb.append(", imagePath1=").append(imagePath1);
        sb.append(", imagePath2=").append(imagePath2);
        sb.append(", imagePath3=").append(imagePath3);
        sb.append(", imagePath4=").append(imagePath4);
        sb.append(", priority=").append(priority);
        sb.append(", executorId=").append(executorId);
        sb.append(", status=").append(status);
        sb.append(", endDate=").append(endDate);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append(", organizationId=").append(organizationId);
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
        EamTicket other = (EamTicket) that;
        return (this.getTicketId() == null ? other.getTicketId() == null : this.getTicketId().equals(other.getTicketId()))
            && (this.getTicketTypeId() == null ? other.getTicketTypeId() == null : this.getTicketTypeId().equals(other.getTicketTypeId()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getImagePath1() == null ? other.getImagePath1() == null : this.getImagePath1().equals(other.getImagePath1()))
            && (this.getImagePath2() == null ? other.getImagePath2() == null : this.getImagePath2().equals(other.getImagePath2()))
            && (this.getImagePath3() == null ? other.getImagePath3() == null : this.getImagePath3().equals(other.getImagePath3()))
            && (this.getImagePath4() == null ? other.getImagePath4() == null : this.getImagePath4().equals(other.getImagePath4()))
            && (this.getPriority() == null ? other.getPriority() == null : this.getPriority().equals(other.getPriority()))
            && (this.getExecutorId() == null ? other.getExecutorId() == null : this.getExecutorId().equals(other.getExecutorId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTicketId() == null) ? 0 : getTicketId().hashCode());
        result = prime * result + ((getTicketTypeId() == null) ? 0 : getTicketTypeId().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getImagePath1() == null) ? 0 : getImagePath1().hashCode());
        result = prime * result + ((getImagePath2() == null) ? 0 : getImagePath2().hashCode());
        result = prime * result + ((getImagePath3() == null) ? 0 : getImagePath3().hashCode());
        result = prime * result + ((getImagePath4() == null) ? 0 : getImagePath4().hashCode());
        result = prime * result + ((getPriority() == null) ? 0 : getPriority().hashCode());
        result = prime * result + ((getExecutorId() == null) ? 0 : getExecutorId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        return result;
    }
}