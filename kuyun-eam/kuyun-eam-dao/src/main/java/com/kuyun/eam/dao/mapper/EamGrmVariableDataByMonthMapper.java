package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableDataByMonth;
import com.kuyun.eam.dao.model.EamGrmVariableDataByMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableDataByMonthMapper {
    long countByExample(EamGrmVariableDataByMonthExample example);

    int deleteByExample(EamGrmVariableDataByMonthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariableDataByMonth record);

    int insertSelective(EamGrmVariableDataByMonth record);

    List<EamGrmVariableDataByMonth> selectByExample(EamGrmVariableDataByMonthExample example);

    EamGrmVariableDataByMonth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariableDataByMonth record, @Param("example") EamGrmVariableDataByMonthExample example);

    int updateByExample(@Param("record") EamGrmVariableDataByMonth record, @Param("example") EamGrmVariableDataByMonthExample example);

    int updateByPrimaryKeySelective(EamGrmVariableDataByMonth record);

    int updateByPrimaryKey(EamGrmVariableDataByMonth record);

    void batchInsert(@Param("items") List<EamGrmVariableDataByMonth> items);
}