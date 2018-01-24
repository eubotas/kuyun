package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentDataGroup;
import com.kuyun.eam.dao.model.EamEquipmentDataGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentDataGroupMapper {
    long countByExample(EamEquipmentDataGroupExample example);

    int deleteByExample(EamEquipmentDataGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamEquipmentDataGroup record);

    int insertSelective(EamEquipmentDataGroup record);

    List<EamEquipmentDataGroup> selectByExample(EamEquipmentDataGroupExample example);

    EamEquipmentDataGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamEquipmentDataGroup record, @Param("example") EamEquipmentDataGroupExample example);

    int updateByExample(@Param("record") EamEquipmentDataGroup record, @Param("example") EamEquipmentDataGroupExample example);

    int updateByPrimaryKeySelective(EamEquipmentDataGroup record);

    int updateByPrimaryKey(EamEquipmentDataGroup record);

    void batchInsert(@Param("items") List<EamEquipmentDataGroup> items);
}