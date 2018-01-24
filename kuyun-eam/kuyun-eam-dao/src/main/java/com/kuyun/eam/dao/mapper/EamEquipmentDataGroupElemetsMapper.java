package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentDataGroupElemets;
import com.kuyun.eam.dao.model.EamEquipmentDataGroupElemetsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentDataGroupElemetsMapper {
    long countByExample(EamEquipmentDataGroupElemetsExample example);

    int deleteByExample(EamEquipmentDataGroupElemetsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EamEquipmentDataGroupElemets record);

    int insertSelective(EamEquipmentDataGroupElemets record);

    List<EamEquipmentDataGroupElemets> selectByExample(EamEquipmentDataGroupElemetsExample example);

    EamEquipmentDataGroupElemets selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EamEquipmentDataGroupElemets record, @Param("example") EamEquipmentDataGroupElemetsExample example);

    int updateByExample(@Param("record") EamEquipmentDataGroupElemets record, @Param("example") EamEquipmentDataGroupElemetsExample example);

    int updateByPrimaryKeySelective(EamEquipmentDataGroupElemets record);

    int updateByPrimaryKey(EamEquipmentDataGroupElemets record);

    void batchInsert(@Param("items") List<EamEquipmentDataGroupElemets> items);
}