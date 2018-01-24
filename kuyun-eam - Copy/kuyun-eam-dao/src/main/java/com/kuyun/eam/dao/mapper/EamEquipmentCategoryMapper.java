package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentCategory;
import com.kuyun.eam.dao.model.EamEquipmentCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentCategoryMapper {
    long countByExample(EamEquipmentCategoryExample example);

    int deleteByExample(EamEquipmentCategoryExample example);

    int deleteByPrimaryKey(Integer equipmentCategoryId);

    int insert(EamEquipmentCategory record);

    int insertSelective(EamEquipmentCategory record);

    List<EamEquipmentCategory> selectByExample(EamEquipmentCategoryExample example);

    EamEquipmentCategory selectByPrimaryKey(Integer equipmentCategoryId);

    int updateByExampleSelective(@Param("record") EamEquipmentCategory record, @Param("example") EamEquipmentCategoryExample example);

    int updateByExample(@Param("record") EamEquipmentCategory record, @Param("example") EamEquipmentCategoryExample example);

    int updateByPrimaryKeySelective(EamEquipmentCategory record);

    int updateByPrimaryKey(EamEquipmentCategory record);

    void batchInsert(@Param("items") List<EamEquipmentCategory> items);
}