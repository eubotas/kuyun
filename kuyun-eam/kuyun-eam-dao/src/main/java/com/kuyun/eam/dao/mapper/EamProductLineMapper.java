package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamProductLine;
import com.kuyun.eam.dao.model.EamProductLineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamProductLineMapper {
    long countByExample(EamProductLineExample example);

    int deleteByExample(EamProductLineExample example);

    int deleteByPrimaryKey(String productLineId);

    int insert(EamProductLine record);

    int insertSelective(EamProductLine record);

    List<EamProductLine> selectByExample(EamProductLineExample example);

    EamProductLine selectByPrimaryKey(String productLineId);

    int updateByExampleSelective(@Param("record") EamProductLine record, @Param("example") EamProductLineExample example);

    int updateByExample(@Param("record") EamProductLine record, @Param("example") EamProductLineExample example);

    int updateByPrimaryKeySelective(EamProductLine record);

    int updateByPrimaryKey(EamProductLine record);

    void batchInsert(@Param("items") List<EamProductLine> items);
}