package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmVariableDataHistoryGroup;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistoryGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmVariableDataHistoryGroupMapper {
    long countByExample(EamGrmVariableDataHistoryGroupExample example);

    int deleteByExample(EamGrmVariableDataHistoryGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmVariableDataHistoryGroup record);

    int insertSelective(EamGrmVariableDataHistoryGroup record);

    List<EamGrmVariableDataHistoryGroup> selectByExample(EamGrmVariableDataHistoryGroupExample example);

    EamGrmVariableDataHistoryGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmVariableDataHistoryGroup record, @Param("example") EamGrmVariableDataHistoryGroupExample example);

    int updateByExample(@Param("record") EamGrmVariableDataHistoryGroup record, @Param("example") EamGrmVariableDataHistoryGroupExample example);

    int updateByPrimaryKeySelective(EamGrmVariableDataHistoryGroup record);

    int updateByPrimaryKey(EamGrmVariableDataHistoryGroup record);

    void batchInsert(@Param("items") List<EamGrmVariableDataHistoryGroup> items);
}