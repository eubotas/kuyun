package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmTypeValue;
import com.kuyun.eam.dao.model.EamAlarmTypeValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmTypeValueMapper {
    long countByExample(EamAlarmTypeValueExample example);

    int deleteByExample(EamAlarmTypeValueExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlarmTypeValue record);

    int insertSelective(EamAlarmTypeValue record);

    List<EamAlarmTypeValue> selectByExample(EamAlarmTypeValueExample example);

    EamAlarmTypeValue selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlarmTypeValue record, @Param("example") EamAlarmTypeValueExample example);

    int updateByExample(@Param("record") EamAlarmTypeValue record, @Param("example") EamAlarmTypeValueExample example);

    int updateByPrimaryKeySelective(EamAlarmTypeValue record);

    int updateByPrimaryKey(EamAlarmTypeValue record);
}