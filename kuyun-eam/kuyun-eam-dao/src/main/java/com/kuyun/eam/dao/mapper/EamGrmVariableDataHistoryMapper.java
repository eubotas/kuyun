package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableDataHistory;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistoryExample;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableDataHistoryMapper {
    long countByExample(EamGrmVariableDataHistoryExample example);

    int deleteByExample(EamGrmVariableDataHistoryExample example);

    int deleteByPrimaryKey(@Param("id") Long id, @Param("updateTime") Date updateTime);

    int insert(EamGrmVariableDataHistory record);

    int insertSelective(EamGrmVariableDataHistory record);

    List<EamGrmVariableDataHistory> selectByExample(EamGrmVariableDataHistoryExample example);

    EamGrmVariableDataHistory selectByPrimaryKey(@Param("id") Long id, @Param("updateTime") Date updateTime);

    int updateByExampleSelective(@Param("record") EamGrmVariableDataHistory record, @Param("example") EamGrmVariableDataHistoryExample example);

    int updateByExample(@Param("record") EamGrmVariableDataHistory record, @Param("example") EamGrmVariableDataHistoryExample example);

    int updateByPrimaryKeySelective(EamGrmVariableDataHistory record);

    int updateByPrimaryKey(EamGrmVariableDataHistory record);

    void batchInsert(@Param("items") List<EamGrmVariableDataHistory> items);
}