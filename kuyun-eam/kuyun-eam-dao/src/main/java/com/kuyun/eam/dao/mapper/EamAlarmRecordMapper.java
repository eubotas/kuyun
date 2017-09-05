package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmRecord;
import com.kuyun.eam.dao.model.EamAlarmRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmRecordMapper {
    long countByExample(EamAlarmRecordExample example);

    int deleteByExample(EamAlarmRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlarmRecord record);

    int insertSelective(EamAlarmRecord record);

    List<EamAlarmRecord> selectByExample(EamAlarmRecordExample example);

    EamAlarmRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlarmRecord record, @Param("example") EamAlarmRecordExample example);

    int updateByExample(@Param("record") EamAlarmRecord record, @Param("example") EamAlarmRecordExample example);

    int updateByPrimaryKeySelective(EamAlarmRecord record);

    int updateByPrimaryKey(EamAlarmRecord record);

    void batchInsert(@Param("items") List<EamAlarmRecord> items);
}