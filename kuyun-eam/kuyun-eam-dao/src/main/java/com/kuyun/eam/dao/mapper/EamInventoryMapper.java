package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamInventory;
import com.kuyun.eam.dao.model.EamInventoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamInventoryMapper {
    long countByExample(EamInventoryExample example);

    int deleteByExample(EamInventoryExample example);

    int deleteByPrimaryKey(Integer inventoryId);

    int insert(EamInventory record);

    int insertSelective(EamInventory record);

    List<EamInventory> selectByExample(EamInventoryExample example);

    EamInventory selectByPrimaryKey(Integer inventoryId);

    int updateByExampleSelective(@Param("record") EamInventory record, @Param("example") EamInventoryExample example);

    int updateByExample(@Param("record") EamInventory record, @Param("example") EamInventoryExample example);

    int updateByPrimaryKeySelective(EamInventory record);

    int updateByPrimaryKey(EamInventory record);
}