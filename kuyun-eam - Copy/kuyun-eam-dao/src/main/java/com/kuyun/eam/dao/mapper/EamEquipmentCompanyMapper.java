package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamEquipmentCompany;
import com.kuyun.eam.dao.model.EamEquipmentCompanyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamEquipmentCompanyMapper {
    long countByExample(EamEquipmentCompanyExample example);

    int deleteByExample(EamEquipmentCompanyExample example);

    int deleteByPrimaryKey(Integer equipmentCompanyId);

    int insert(EamEquipmentCompany record);

    int insertSelective(EamEquipmentCompany record);

    List<EamEquipmentCompany> selectByExample(EamEquipmentCompanyExample example);

    EamEquipmentCompany selectByPrimaryKey(Integer equipmentCompanyId);

    int updateByExampleSelective(@Param("record") EamEquipmentCompany record, @Param("example") EamEquipmentCompanyExample example);

    int updateByExample(@Param("record") EamEquipmentCompany record, @Param("example") EamEquipmentCompanyExample example);

    int updateByPrimaryKeySelective(EamEquipmentCompany record);

    int updateByPrimaryKey(EamEquipmentCompany record);

    void batchInsert(@Param("items") List<EamEquipmentCompany> items);
}