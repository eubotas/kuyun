package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmEquipmentVariable;
import com.kuyun.eam.dao.model.EamGrmEquipmentVariableExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmEquipmentVariableMapper {
    long countByExample(EamGrmEquipmentVariableExample example);

    int deleteByExample(EamGrmEquipmentVariableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmEquipmentVariable record);

    int insertSelective(EamGrmEquipmentVariable record);

    List<EamGrmEquipmentVariable> selectByExample(EamGrmEquipmentVariableExample example);

    EamGrmEquipmentVariable selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmEquipmentVariable record, @Param("example") EamGrmEquipmentVariableExample example);

    int updateByExample(@Param("record") EamGrmEquipmentVariable record, @Param("example") EamGrmEquipmentVariableExample example);

    int updateByPrimaryKeySelective(EamGrmEquipmentVariable record);

    int updateByPrimaryKey(EamGrmEquipmentVariable record);

    void batchInsert(@Param("items") List<EamGrmEquipmentVariable> items);
}