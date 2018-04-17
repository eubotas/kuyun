package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamGrmEquipment;
import com.kuyun.eam.dao.model.EamGrmEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamGrmEquipmentMapper {
    long countByExample(EamGrmEquipmentExample example);

    int deleteByExample(EamGrmEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamGrmEquipment record);

    int insertSelective(EamGrmEquipment record);

    List<EamGrmEquipment> selectByExample(EamGrmEquipmentExample example);

    EamGrmEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamGrmEquipment record, @Param("example") EamGrmEquipmentExample example);

    int updateByExample(@Param("record") EamGrmEquipment record, @Param("example") EamGrmEquipmentExample example);

    int updateByPrimaryKeySelective(EamGrmEquipment record);

    int updateByPrimaryKey(EamGrmEquipment record);

    void batchInsert(@Param("items") List<EamGrmEquipment> items);
}