package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariable;
import com.kuyun.eam.dao.model.EamGrmVariableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableMapper {
    long countByExample(EamGrmVariableExample example);

    int deleteByExample(EamGrmVariableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariable record);

    int insertSelective(EamGrmVariable record);

    List<EamGrmVariable> selectByExample(EamGrmVariableExample example);

    EamGrmVariable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariable record, @Param("example") EamGrmVariableExample example);

    int updateByExample(@Param("record") EamGrmVariable record, @Param("example") EamGrmVariableExample example);

    int updateByPrimaryKeySelective(EamGrmVariable record);

    int updateByPrimaryKey(EamGrmVariable record);

    void batchInsert(@Param("items") List<EamGrmVariable> items);
}