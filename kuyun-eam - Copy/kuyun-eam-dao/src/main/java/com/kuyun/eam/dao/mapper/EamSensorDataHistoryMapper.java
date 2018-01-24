package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamSensorDataHistory;
import com.kuyun.eam.dao.model.EamSensorDataHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamSensorDataHistoryMapper {
    long countByExample(EamSensorDataHistoryExample example);

    int deleteByExample(EamSensorDataHistoryExample example);

    int deleteByPrimaryKey(Integer sensorDataId);

    int insert(EamSensorDataHistory record);

    int insertSelective(EamSensorDataHistory record);

    List<EamSensorDataHistory> selectByExample(EamSensorDataHistoryExample example);

    EamSensorDataHistory selectByPrimaryKey(Integer sensorDataId);

    int updateByExampleSelective(@Param("record") EamSensorDataHistory record, @Param("example") EamSensorDataHistoryExample example);

    int updateByExample(@Param("record") EamSensorDataHistory record, @Param("example") EamSensorDataHistoryExample example);

    int updateByPrimaryKeySelective(EamSensorDataHistory record);

    int updateByPrimaryKey(EamSensorDataHistory record);

    void batchInsert(@Param("items") List<EamSensorDataHistory> items);
}