package com.kuyun.upms.dao.model;

import java.io.Serializable;

public class UpmsUserCompany implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userCompanyId;

    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * 公司编号
     *
     * @mbg.generated
     */
    private Integer companyId;

    private static final long serialVersionUID = 1L;

    public Integer getUserCompanyId() {
        return userCompanyId;
    }

    public void setUserCompanyId(Integer userCompanyId) {
        this.userCompanyId = userCompanyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userCompanyId=").append(userCompanyId);
        sb.append(", userId=").append(userId);
        sb.append(", companyId=").append(companyId);
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
        UpmsUserCompany other = (UpmsUserCompany) that;
        return (this.getUserCompanyId() == null ? other.getUserCompanyId() == null : this.getUserCompanyId().equals(other.getUserCompanyId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUserCompanyId() == null) ? 0 : getUserCompanyId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        return result;
    }
}