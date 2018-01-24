package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentModel;
import com.kuyun.eam.dao.model.EamEquipmentModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentModelMapper {
    long countByExample(EamEquipmentModelExample example);

    int deleteByExample(EamEquipmentModelExample example);

    int deleteByPrimaryKey(Integer equipmentModelId);

    int insert(EamEquipmentModel record);

    int insertSelective(EamEquipmentModel record);

    List<EamEquipmentModel> selectByExample(EamEquipmentModelExample example);

    EamEquipmentModel selectByPrimaryKey(Integer equipmentModelId);

    int updateByExampleSelective(@Param("record") EamEquipmentModel record, @Param("example") EamEquipmentModelExample example);

    int updateByExample(@Param("record") EamEquipmentModel record, @Param("example") EamEquipmentModelExample example);

    int updateByPrimaryKeySelective(EamEquipmentModel record);

    int updateByPrimaryKey(EamEquipmentModel record);

    void batchInsert(@Param("items") List<EamEquipmentModel> items);
}