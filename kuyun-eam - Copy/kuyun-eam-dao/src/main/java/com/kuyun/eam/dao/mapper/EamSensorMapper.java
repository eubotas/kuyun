package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamSensor;
import com.kuyun.eam.dao.model.EamSensorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamSensorMapper {
    long countByExample(EamSensorExample example);

    int deleteByExample(EamSensorExample example);

    int deleteByPrimaryKey(Integer sensorId);

    int insert(EamSensor record);

    int insertSelective(EamSensor record);

    List<EamSensor> selectByExample(EamSensorExample example);

    EamSensor selectByPrimaryKey(Integer sensorId);

    int updateByExampleSelective(@Param("record") EamSensor record, @Param("example") EamSensorExample example);

    int updateByExample(@Param("record") EamSensor record, @Param("example") EamSensorExample example);

    int updateByPrimaryKeySelective(EamSensor record);

    int updateByPrimaryKey(EamSensor record);

    void batchInsert(@Param("items") List<EamSensor> items);
}