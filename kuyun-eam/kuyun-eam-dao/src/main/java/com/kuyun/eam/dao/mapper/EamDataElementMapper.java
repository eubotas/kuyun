package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamDataElement;
import com.kuyun.eam.dao.model.EamDataElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamDataElementMapper {
    long countByExample(EamDataElementExample example);

    int deleteByExample(EamDataElementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamDataElement record);

    int insertSelective(EamDataElement record);

    List<EamDataElement> selectByExample(EamDataElementExample example);

    EamDataElement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamDataElement record, @Param("example") EamDataElementExample example);

    int updateByExample(@Param("record") EamDataElement record, @Param("example") EamDataElementExample example);

    int updateByPrimaryKeySelective(EamDataElement record);

    int updateByPrimaryKey(EamDataElement record);

    void batchInsert(@Param("items") List<EamDataElement> items);
}