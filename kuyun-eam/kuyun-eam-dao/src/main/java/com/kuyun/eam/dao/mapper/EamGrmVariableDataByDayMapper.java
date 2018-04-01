package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableDataByDay;
import com.kuyun.eam.dao.model.EamGrmVariableDataByDayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableDataByDayMapper {
    long countByExample(EamGrmVariableDataByDayExample example);

    int deleteByExample(EamGrmVariableDataByDayExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariableDataByDay record);

    int insertSelective(EamGrmVariableDataByDay record);

    List<EamGrmVariableDataByDay> selectByExample(EamGrmVariableDataByDayExample example);

    EamGrmVariableDataByDay selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariableDataByDay record, @Param("example") EamGrmVariableDataByDayExample example);

    int updateByExample(@Param("record") EamGrmVariableDataByDay record, @Param("example") EamGrmVariableDataByDayExample example);

    int updateByPrimaryKeySelective(EamGrmVariableDataByDay record);

    int updateByPrimaryKey(EamGrmVariableDataByDay record);

    void batchInsert(@Param("items") List<EamGrmVariableDataByDay> items);
}