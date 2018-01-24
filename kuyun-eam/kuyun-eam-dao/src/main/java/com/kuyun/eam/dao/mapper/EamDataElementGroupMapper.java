package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamDataElementGroup;
import com.kuyun.eam.dao.model.EamDataElementGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamDataElementGroupMapper {
    long countByExample(EamDataElementGroupExample example);

    int deleteByExample(EamDataElementGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamDataElementGroup record);

    int insertSelective(EamDataElementGroup record);

    List<EamDataElementGroup> selectByExample(EamDataElementGroupExample example);

    EamDataElementGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamDataElementGroup record, @Param("example") EamDataElementGroupExample example);

    int updateByExample(@Param("record") EamDataElementGroup record, @Param("example") EamDataElementGroupExample example);

    int updateByPrimaryKeySelective(EamDataElementGroup record);

    int updateByPrimaryKey(EamDataElementGroup record);

    void batchInsert(@Param("items") List<EamDataElementGroup> items);
}