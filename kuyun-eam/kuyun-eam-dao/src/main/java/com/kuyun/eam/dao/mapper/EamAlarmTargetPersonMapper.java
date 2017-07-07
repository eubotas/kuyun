package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmTargetPerson;
import com.kuyun.eam.dao.model.EamAlarmTargetPersonExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmTargetPersonMapper {
    long countByExample(EamAlarmTargetPersonExample example);

    int deleteByExample(EamAlarmTargetPersonExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlarmTargetPerson record);

    int insertSelective(EamAlarmTargetPerson record);

    List<EamAlarmTargetPerson> selectByExample(EamAlarmTargetPersonExample example);

    EamAlarmTargetPerson selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlarmTargetPerson record, @Param("example") EamAlarmTargetPersonExample example);

    int updateByExample(@Param("record") EamAlarmTargetPerson record, @Param("example") EamAlarmTargetPersonExample example);

    int updateByPrimaryKeySelective(EamAlarmTargetPerson record);

    int updateByPrimaryKey(EamAlarmTargetPerson record);
}