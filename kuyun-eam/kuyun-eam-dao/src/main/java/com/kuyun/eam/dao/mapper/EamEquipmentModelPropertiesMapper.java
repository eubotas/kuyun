package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentModelProperties;
import com.kuyun.eam.dao.model.EamEquipmentModelPropertiesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentModelPropertiesMapper {
    long countByExample(EamEquipmentModelPropertiesExample example);

    int deleteByExample(EamEquipmentModelPropertiesExample example);

    int deleteByPrimaryKey(Integer equipmentModelPropertyId);

    int insert(EamEquipmentModelProperties record);

    int insertSelective(EamEquipmentModelProperties record);

    List<EamEquipmentModelProperties> selectByExample(EamEquipmentModelPropertiesExample example);

    EamEquipmentModelProperties selectByPrimaryKey(Integer equipmentModelPropertyId);

    int updateByExampleSelective(@Param("record") EamEquipmentModelProperties record, @Param("example") EamEquipmentModelPropertiesExample example);

    int updateByExample(@Param("record") EamEquipmentModelProperties record, @Param("example") EamEquipmentModelPropertiesExample example);

    int updateByPrimaryKeySelective(EamEquipmentModelProperties record);

    int updateByPrimaryKey(EamEquipmentModelProperties record);
}