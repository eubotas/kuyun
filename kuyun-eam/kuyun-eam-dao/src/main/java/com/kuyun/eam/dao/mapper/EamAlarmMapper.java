package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamAlarmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmMapper {
    long countByExample(EamAlarmExample example);

    int deleteByExample(EamAlarmExample example);

    int deleteByPrimaryKey(Integer alarmId);

    int insert(EamAlarm record);

    int insertSelective(EamAlarm record);

    List<EamAlarm> selectByExample(EamAlarmExample example);

    EamAlarm selectByPrimaryKey(Integer alarmId);

    int updateByExampleSelective(@Param("record") EamAlarm record, @Param("example") EamAlarmExample example);

    int updateByExample(@Param("record") EamAlarm record, @Param("example") EamAlarmExample example);

    int updateByPrimaryKeySelective(EamAlarm record);

    int updateByPrimaryKey(EamAlarm record);

    void batchInsert(@Param("items") List<EamAlarm> items);
}