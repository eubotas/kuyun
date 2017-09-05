package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamAlarmData;
import com.kuyun.eam.dao.model.EamAlarmDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamAlarmDataMapper {
    long countByExample(EamAlarmDataExample example);

    int deleteByExample(EamAlarmDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamAlarmData record);

    int insertSelective(EamAlarmData record);

    List<EamAlarmData> selectByExample(EamAlarmDataExample example);

    EamAlarmData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamAlarmData record, @Param("example") EamAlarmDataExample example);

    int updateByExample(@Param("record") EamAlarmData record, @Param("example") EamAlarmDataExample example);

    int updateByPrimaryKeySelective(EamAlarmData record);

    int updateByPrimaryKey(EamAlarmData record);

    void batchInsert(@Param("items") List<EamAlarmData> items);
}