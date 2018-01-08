package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableData;
import com.kuyun.eam.dao.model.EamGrmVariableDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableDataMapper {
    long countByExample(EamGrmVariableDataExample example);

    int deleteByExample(EamGrmVariableDataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariableData record);

    int insertSelective(EamGrmVariableData record);

    List<EamGrmVariableData> selectByExample(EamGrmVariableDataExample example);

    EamGrmVariableData selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariableData record, @Param("example") EamGrmVariableDataExample example);

    int updateByExample(@Param("record") EamGrmVariableData record, @Param("example") EamGrmVariableDataExample example);

    int updateByPrimaryKeySelective(EamGrmVariableData record);

    int updateByPrimaryKey(EamGrmVariableData record);

    void batchInsert(@Param("items") List<EamGrmVariableData> items);
}