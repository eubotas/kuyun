package com.kuyun.upms.dao.model;

import com.kuyun.common.dao.model.BaseEntity;
import java.io.Serializable;
import java.util.List;

public class UpmsUserCompany extends BaseEntity implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer userCompanyId;
    private Integer parentId;
    private List<Integer> childCompanyIds;
    /**
     * 用户编号
     *
     * @mbg.generated
     */
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

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

    public List<Integer> getChildCompanyIds() {
        return childCompanyIds;
    }

    public void setChildCompanyIds(List<Integer> childCompanyIds) {
        this.childCompanyIds = childCompanyIds;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userCompanyId=").append(userCompanyId);
        sb.append(", userId=").append(userId);
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