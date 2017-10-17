package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmRecordHistory;
import com.kuyun.eam.dao.model.EamAlarmRecordHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmRecordHistoryMapper {
    long countByExample(EamAlarmRecordHistoryExample example);

    int deleteByExample(EamAlarmRecordHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlarmRecordHistory record);

    int insertSelective(EamAlarmRecordHistory record);

    List<EamAlarmRecordHistory> selectByExample(EamAlarmRecordHistoryExample example);

    EamAlarmRecordHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlarmRecordHistory record, @Param("example") EamAlarmRecordHistoryExample example);

    int updateByExample(@Param("record") EamAlarmRecordHistory record, @Param("example") EamAlarmRecordHistoryExample example);

    int updateByPrimaryKeySelective(EamAlarmRecordHistory record);

    int updateByPrimaryKey(EamAlarmRecordHistory record);

    void batchInsert(@Param("items") List<EamAlarmRecordHistory> items);
}