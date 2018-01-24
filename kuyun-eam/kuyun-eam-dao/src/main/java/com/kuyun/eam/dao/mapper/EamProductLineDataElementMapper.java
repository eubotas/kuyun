package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamProductLineDataElement;
import com.kuyun.eam.dao.model.EamProductLineDataElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamProductLineDataElementMapper {
    long countByExample(EamProductLineDataElementExample example);

    int deleteByExample(EamProductLineDataElementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamProductLineDataElement record);

    int insertSelective(EamProductLineDataElement record);

    List<EamProductLineDataElement> selectByExample(EamProductLineDataElementExample example);

    EamProductLineDataElement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamProductLineDataElement record, @Param("example") EamProductLineDataElementExample example);

    int updateByExample(@Param("record") EamProductLineDataElement record, @Param("example") EamProductLineDataElementExample example);

    int updateByPrimaryKeySelective(EamProductLineDataElement record);

    int updateByPrimaryKey(EamProductLineDataElement record);

    void batchInsert(@Param("items") List<EamProductLineDataElement> items);
}