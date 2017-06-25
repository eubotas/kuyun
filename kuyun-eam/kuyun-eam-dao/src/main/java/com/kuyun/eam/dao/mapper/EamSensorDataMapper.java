package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.dao.model.EamSensorDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamSensorDataMapper {
    long countByExample(EamSensorDataExample example);

    int deleteByExample(EamSensorDataExample example);

    int deleteByPrimaryKey(Integer sensorDataId);

    int insert(EamSensorData record);

    int insertSelective(EamSensorData record);

    List<EamSensorData> selectByExample(EamSensorDataExample example);

    EamSensorData selectByPrimaryKey(Integer sensorDataId);

    int updateByExampleSelective(@Param("record") EamSensorData record, @Param("example") EamSensorDataExample example);

    int updateByExample(@Param("record") EamSensorData record, @Param("example") EamSensorDataExample example);

    int updateByPrimaryKeySelective(EamSensorData record);

    int updateByPrimaryKey(EamSensorData record);
}