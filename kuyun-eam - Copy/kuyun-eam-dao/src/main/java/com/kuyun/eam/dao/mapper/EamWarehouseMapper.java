package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamWarehouse;
import com.kuyun.eam.dao.model.EamWarehouseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamWarehouseMapper {
    long countByExample(EamWarehouseExample example);

    int deleteByExample(EamWarehouseExample example);

    int deleteByPrimaryKey(Integer warehouseId);

    int insert(EamWarehouse record);

    int insertSelective(EamWarehouse record);

    List<EamWarehouse> selectByExample(EamWarehouseExample example);

    EamWarehouse selectByPrimaryKey(Integer warehouseId);

    int updateByExampleSelective(@Param("record") EamWarehouse record, @Param("example") EamWarehouseExample example);

    int updateByExample(@Param("record") EamWarehouse record, @Param("example") EamWarehouseExample example);

    int updateByPrimaryKeySelective(EamWarehouse record);

    int updateByPrimaryKey(EamWarehouse record);

    void batchInsert(@Param("items") List<EamWarehouse> items);
}