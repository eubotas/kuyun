package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmTargetOrganization;
import com.kuyun.eam.dao.model.EamAlarmTargetOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmTargetOrganizationMapper {
    long countByExample(EamAlarmTargetOrganizationExample example);

    int deleteByExample(EamAlarmTargetOrganizationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlarmTargetOrganization record);

    int insertSelective(EamAlarmTargetOrganization record);

    List<EamAlarmTargetOrganization> selectByExample(EamAlarmTargetOrganizationExample example);

    EamAlarmTargetOrganization selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlarmTargetOrganization record, @Param("example") EamAlarmTargetOrganizationExample example);

    int updateByExample(@Param("record") EamAlarmTargetOrganization record, @Param("example") EamAlarmTargetOrganizationExample example);

    int updateByPrimaryKeySelective(EamAlarmTargetOrganization record);

    int updateByPrimaryKey(EamAlarmTargetOrganization record);
}