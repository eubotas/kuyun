package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamSensorWriteData;
import com.kuyun.eam.dao.model.EamSensorWriteDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamSensorWriteDataMapper {
    long countByExample(EamSensorWriteDataExample example);

    int deleteByExample(EamSensorWriteDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamSensorWriteData record);

    int insertSelective(EamSensorWriteData record);

    List<EamSensorWriteData> selectByExample(EamSensorWriteDataExample example);

    EamSensorWriteData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamSensorWriteData record, @Param("example") EamSensorWriteDataExample example);

    int updateByExample(@Param("record") EamSensorWriteData record, @Param("example") EamSensorWriteDataExample example);

    int updateByPrimaryKeySelective(EamSensorWriteData record);

    int updateByPrimaryKey(EamSensorWriteData record);

    void batchInsert(@Param("items") List<EamSensorWriteData> items);
}