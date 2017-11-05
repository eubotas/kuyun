package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamDtuEquipment;
import com.kuyun.eam.dao.model.EamDtuEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamDtuEquipmentMapper {
    long countByExample(EamDtuEquipmentExample example);

    int deleteByExample(EamDtuEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamDtuEquipment record);

    int insertSelective(EamDtuEquipment record);

    List<EamDtuEquipment> selectByExample(EamDtuEquipmentExample example);

    EamDtuEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamDtuEquipment record, @Param("example") EamDtuEquipmentExample example);

    int updateByExample(@Param("record") EamDtuEquipment record, @Param("example") EamDtuEquipmentExample example);

    int updateByPrimaryKeySelective(EamDtuEquipment record);

    int updateByPrimaryKey(EamDtuEquipment record);

    void batchInsert(@Param("items") List<EamDtuEquipment> items);
}