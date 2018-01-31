package com.kuyun.eam.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.util.Date;

public class EamAlertMessage extends BaseEntity implements Serializable {
    private Integer id;

    private Integer userId;

    private String messageTitle;

    /**
     * 消息内容
     *
     * @mbg.generated
     */
    private String content;

    private Boolean readFlag;

    private Date readTime;

    /**
     * 消息发送开始日期
     *
     * @mbg.generated
     */
    private Date alertStartDate;

    /**
     * 消息发送结束日期, 超过结束日期后不提示
     *
     * @mbg.generated
     */
    private Date alertEndDate;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Boolean readFlag) {
        this.readFlag = readFlag;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    public Date getAlertStartDate() {
        return alertStartDate;
    }

    public void setAlertStartDate(Date alertStartDate) {
        this.alertStartDate = alertStartDate;
    }

    public Date getAlertEndDate() {
        return alertEndDate;
    }

    public void setAlertEndDate(Date alertEndDate) {
        this.alertEndDate = alertEndDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", messageTitle=").append(messageTitle);
        sb.append(", content=").append(content);
        sb.append(", readFlag=").append(readFlag);
        sb.append(", readTime=").append(readTime);
        sb.append(", alertStartDate=").append(alertStartDate);
        sb.append(", alertEndDate=").append(alertEndDate);
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
        EamAlertMessage other = (EamAlertMessage) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMessageTitle() == null ? other.getMessageTitle() == null : this.getMessageTitle().equals(other.getMessageTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReadFlag() == null ? other.getReadFlag() == null : this.getReadFlag().equals(other.getReadFlag()))
            && (this.getReadTime() == null ? other.getReadTime() == null : this.getReadTime().equals(other.getReadTime()))
            && (this.getAlertStartDate() == null ? other.getAlertStartDate() == null : this.getAlertStartDate().equals(other.getAlertStartDate()))
            && (this.getAlertEndDate() == null ? other.getAlertEndDate() == null : this.getAlertEndDate().equals(other.getAlertEndDate()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleteFlag() == null ? other.getDeleteFlag() == null : this.getDeleteFlag().equals(other.getDeleteFlag()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMessageTitle() == null) ? 0 : getMessageTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReadFlag() == null) ? 0 : getReadFlag().hashCode());
        result = prime * result + ((getReadTime() == null) ? 0 : getReadTime().hashCode());
        result = prime * result + ((getAlertStartDate() == null) ? 0 : getAlertStartDate().hashCode());
        result = prime * result + ((getAlertEndDate() == null) ? 0 : getAlertEndDate().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleteFlag() == null) ? 0 : getDeleteFlag().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        return result;
    }
}