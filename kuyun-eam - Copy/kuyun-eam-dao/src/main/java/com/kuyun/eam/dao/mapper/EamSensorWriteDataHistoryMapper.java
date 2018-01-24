package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamSensorWriteDataHistory;
import com.kuyun.eam.dao.model.EamSensorWriteDataHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamSensorWriteDataHistoryMapper {
    long countByExample(EamSensorWriteDataHistoryExample example);

    int deleteByExample(EamSensorWriteDataHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamSensorWriteDataHistory record);

    int insertSelective(EamSensorWriteDataHistory record);

    List<EamSensorWriteDataHistory> selectByExample(EamSensorWriteDataHistoryExample example);

    EamSensorWriteDataHistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamSensorWriteDataHistory record, @Param("example") EamSensorWriteDataHistoryExample example);

    int updateByExample(@Param("record") EamSensorWriteDataHistory record, @Param("example") EamSensorWriteDataHistoryExample example);

    int updateByPrimaryKeySelective(EamSensorWriteDataHistory record);

    int updateByPrimaryKey(EamSensorWriteDataHistory record);

    void batchInsert(@Param("items") List<EamSensorWriteDataHistory> items);
}