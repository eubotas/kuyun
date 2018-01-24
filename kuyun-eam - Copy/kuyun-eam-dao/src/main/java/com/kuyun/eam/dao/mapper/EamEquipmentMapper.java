package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentMapper {
    long countByExample(EamEquipmentExample example);

    int deleteByExample(EamEquipmentExample example);

    int deleteByPrimaryKey(String equipmentId);

    int insert(EamEquipment record);

    int insertSelective(EamEquipment record);

    List<EamEquipment> selectByExample(EamEquipmentExample example);

    EamEquipment selectByPrimaryKey(String equipmentId);

    int updateByExampleSelective(@Param("record") EamEquipment record, @Param("example") EamEquipmentExample example);

    int updateByExample(@Param("record") EamEquipment record, @Param("example") EamEquipmentExample example);

    int updateByPrimaryKeySelective(EamEquipment record);

    int updateByPrimaryKey(EamEquipment record);

    void batchInsert(@Param("items") List<EamEquipment> items);
}