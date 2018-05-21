package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamShiftDataElement;
import com.kuyun.eam.dao.model.EamShiftDataElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamShiftDataElementMapper {
    long countByExample(EamShiftDataElementExample example);

    int deleteByExample(EamShiftDataElementExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamShiftDataElement record);

    int insertSelective(EamShiftDataElement record);

    List<EamShiftDataElement> selectByExample(EamShiftDataElementExample example);

    EamShiftDataElement selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamShiftDataElement record, @Param("example") EamShiftDataElementExample example);

    int updateByExample(@Param("record") EamShiftDataElement record, @Param("example") EamShiftDataElementExample example);

    int updateByPrimaryKeySelective(EamShiftDataElement record);

    int updateByPrimaryKey(EamShiftDataElement record);

    void batchInsert(@Param("items") List<EamShiftDataElement> items);
}