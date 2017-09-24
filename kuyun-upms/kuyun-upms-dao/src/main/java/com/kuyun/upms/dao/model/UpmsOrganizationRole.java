package com.kuyun.upms.dao.model;

import java.io.Serializable;

public class UpmsOrganizationRole implements Serializable {
    /**
     * 编号
     *
     * @mbg.generated
     */
    private Integer organizationRoleId;

    /**
     * 组织编号
     *
     * @mbg.generated
     */
    private Integer organizationId;

    /**
     * 角色编号
     *
     * @mbg.generated
     */
    private Integer roleId;

    private static final long serialVersionUID = 1L;

    public Integer getOrganizationRoleId() {
        return organizationRoleId;
    }

    public void setOrganizationRoleId(Integer organizationRoleId) {
        this.organizationRoleId = organizationRoleId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", organizationRoleId=").append(organizationRoleId);
        sb.append(", organizationId=").append(organizationId);
        sb.append(", roleId=").append(roleId);
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
        UpmsOrganizationRole other = (UpmsOrganizationRole) that;
        return (this.getOrganizationRoleId() == null ? other.getOrganizationRoleId() == null : this.getOrganizationRoleId().equals(other.getOrganizationRoleId()))
            && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrganizationRoleId() == null) ? 0 : getOrganizationRoleId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        return result;
    }
}