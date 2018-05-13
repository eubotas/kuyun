package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentProperty;
import com.kuyun.eam.dao.model.EamEquipmentPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentPropertyMapper {
    long countByExample(EamEquipmentPropertyExample example);

    int deleteByExample(EamEquipmentPropertyExample example);

    int deleteByPrimaryKey(Integer equipmentPropertyId);

    int insert(EamEquipmentProperty record);

    int insertSelective(EamEquipmentProperty record);

    List<EamEquipmentProperty> selectByExample(EamEquipmentPropertyExample example);

    EamEquipmentProperty selectByPrimaryKey(Integer equipmentPropertyId);

    int updateByExampleSelective(@Param("record") EamEquipmentProperty record, @Param("example") EamEquipmentPropertyExample example);

    int updateByExample(@Param("record") EamEquipmentProperty record, @Param("example") EamEquipmentPropertyExample example);

    int updateByPrimaryKeySelective(EamEquipmentProperty record);

    int updateByPrimaryKey(EamEquipmentProperty record);

    void batchInsert(@Param("items") List<EamEquipmentProperty> items);
}