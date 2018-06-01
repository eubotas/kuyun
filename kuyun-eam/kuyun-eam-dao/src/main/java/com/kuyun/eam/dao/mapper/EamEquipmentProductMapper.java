package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentProduct;
import com.kuyun.eam.dao.model.EamEquipmentProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentProductMapper {
    long countByExample(EamEquipmentProductExample example);

    int deleteByExample(EamEquipmentProductExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamEquipmentProduct record);

    int insertSelective(EamEquipmentProduct record);

    List<EamEquipmentProduct> selectByExample(EamEquipmentProductExample example);

    EamEquipmentProduct selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamEquipmentProduct record, @Param("example") EamEquipmentProductExample example);

    int updateByExample(@Param("record") EamEquipmentProduct record, @Param("example") EamEquipmentProductExample example);

    int updateByPrimaryKeySelective(EamEquipmentProduct record);

    int updateByPrimaryKey(EamEquipmentProduct record);

    void batchInsert(@Param("items") List<EamEquipmentProduct> items);
}