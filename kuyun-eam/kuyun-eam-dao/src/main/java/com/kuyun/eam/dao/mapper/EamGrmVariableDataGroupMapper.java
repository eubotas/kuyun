package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableDataGroup;
import com.kuyun.eam.dao.model.EamGrmVariableDataGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableDataGroupMapper {
    long countByExample(EamGrmVariableDataGroupExample example);

    int deleteByExample(EamGrmVariableDataGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariableDataGroup record);

    int insertSelective(EamGrmVariableDataGroup record);

    List<EamGrmVariableDataGroup> selectByExample(EamGrmVariableDataGroupExample example);

    EamGrmVariableDataGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariableDataGroup record, @Param("example") EamGrmVariableDataGroupExample example);

    int updateByExample(@Param("record") EamGrmVariableDataGroup record, @Param("example") EamGrmVariableDataGroupExample example);

    int updateByPrimaryKeySelective(EamGrmVariableDataGroup record);

    int updateByPrimaryKey(EamGrmVariableDataGroup record);

    void batchInsert(@Param("items") List<EamGrmVariableDataGroup> items);
}