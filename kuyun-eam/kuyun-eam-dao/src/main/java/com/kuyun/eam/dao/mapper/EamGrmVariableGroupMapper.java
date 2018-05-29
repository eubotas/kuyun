package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableGroup;
import com.kuyun.eam.dao.model.EamGrmVariableGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableGroupMapper {
    long countByExample(EamGrmVariableGroupExample example);

    int deleteByExample(EamGrmVariableGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariableGroup record);

    int insertSelective(EamGrmVariableGroup record);

    List<EamGrmVariableGroup> selectByExample(EamGrmVariableGroupExample example);

    EamGrmVariableGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariableGroup record, @Param("example") EamGrmVariableGroupExample example);

    int updateByExample(@Param("record") EamGrmVariableGroup record, @Param("example") EamGrmVariableGroupExample example);

    int updateByPrimaryKeySelective(EamGrmVariableGroup record);

    int updateByPrimaryKey(EamGrmVariableGroup record);

    void batchInsert(@Param("items") List<EamGrmVariableGroup> items);
}