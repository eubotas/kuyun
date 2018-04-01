package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableDataByYear;
import com.kuyun.eam.dao.model.EamGrmVariableDataByYearExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableDataByYearMapper {
    long countByExample(EamGrmVariableDataByYearExample example);

    int deleteByExample(EamGrmVariableDataByYearExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariableDataByYear record);

    int insertSelective(EamGrmVariableDataByYear record);

    List<EamGrmVariableDataByYear> selectByExample(EamGrmVariableDataByYearExample example);

    EamGrmVariableDataByYear selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariableDataByYear record, @Param("example") EamGrmVariableDataByYearExample example);

    int updateByExample(@Param("record") EamGrmVariableDataByYear record, @Param("example") EamGrmVariableDataByYearExample example);

    int updateByPrimaryKeySelective(EamGrmVariableDataByYear record);

    int updateByPrimaryKey(EamGrmVariableDataByYear record);

    void batchInsert(@Param("items") List<EamGrmVariableDataByYear> items);
}