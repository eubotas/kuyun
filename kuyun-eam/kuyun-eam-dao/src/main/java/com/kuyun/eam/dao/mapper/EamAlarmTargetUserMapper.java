package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmTargetUser;
import com.kuyun.eam.dao.model.EamAlarmTargetUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmTargetUserMapper {
    long countByExample(EamAlarmTargetUserExample example);

    int deleteByExample(EamAlarmTargetUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlarmTargetUser record);

    int insertSelective(EamAlarmTargetUser record);

    List<EamAlarmTargetUser> selectByExample(EamAlarmTargetUserExample example);

    EamAlarmTargetUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlarmTargetUser record, @Param("example") EamAlarmTargetUserExample example);

    int updateByExample(@Param("record") EamAlarmTargetUser record, @Param("example") EamAlarmTargetUserExample example);

    int updateByPrimaryKeySelective(EamAlarmTargetUser record);

    int updateByPrimaryKey(EamAlarmTargetUser record);
}