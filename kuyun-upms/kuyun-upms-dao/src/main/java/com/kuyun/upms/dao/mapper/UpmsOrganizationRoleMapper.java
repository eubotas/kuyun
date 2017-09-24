package com.kuyun.upms.dao.mapper;

import com.kuyun.upms.dao.model.UpmsOrganizationRole;
import com.kuyun.upms.dao.model.UpmsOrganizationRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsOrganizationRoleMapper {
    long countByExample(UpmsOrganizationRoleExample example);

    int deleteByExample(UpmsOrganizationRoleExample example);

    int deleteByPrimaryKey(Integer organizationRoleId);

    int insert(UpmsOrganizationRole record);

    int insertSelective(UpmsOrganizationRole record);

    List<UpmsOrganizationRole> selectByExample(UpmsOrganizationRoleExample example);

    UpmsOrganizationRole selectByPrimaryKey(Integer organizationRoleId);

    int updateByExampleSelective(@Param("record") UpmsOrganizationRole record, @Param("example") UpmsOrganizationRoleExample example);

    int updateByExample(@Param("record") UpmsOrganizationRole record, @Param("example") UpmsOrganizationRoleExample example);

    int updateByPrimaryKeySelective(UpmsOrganizationRole record);

    int updateByPrimaryKey(UpmsOrganizationRole record);

    void batchInsert(@Param("items") List<UpmsOrganizationRole> items);
}