package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamShiftDataElementValue;
import com.kuyun.eam.dao.model.EamShiftDataElementValueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamShiftDataElementValueMapper {
    long countByExample(EamShiftDataElementValueExample example);

    int deleteByExample(EamShiftDataElementValueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(EamShiftDataElementValue record);

    int insertSelective(EamShiftDataElementValue record);

    List<EamShiftDataElementValue> selectByExample(EamShiftDataElementValueExample example);

    EamShiftDataElementValue selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") EamShiftDataElementValue record, @Param("example") EamShiftDataElementValueExample example);

    int updateByExample(@Param("record") EamShiftDataElementValue record, @Param("example") EamShiftDataElementValueExample example);

    int updateByPrimaryKeySelective(EamShiftDataElementValue record);

    int updateByPrimaryKey(EamShiftDataElementValue record);

    void batchInsert(@Param("items") List<EamShiftDataElementValue> items);
}