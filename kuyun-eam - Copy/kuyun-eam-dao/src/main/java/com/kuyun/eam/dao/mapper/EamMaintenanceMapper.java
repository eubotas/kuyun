package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamMaintenance;
import com.kuyun.eam.dao.model.EamMaintenanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamMaintenanceMapper {
    long countByExample(EamMaintenanceExample example);

    int deleteByExample(EamMaintenanceExample example);

    int deleteByPrimaryKey(Integer maintenanceId);

    int insert(EamMaintenance record);

    int insertSelective(EamMaintenance record);

    List<EamMaintenance> selectByExample(EamMaintenanceExample example);

    EamMaintenance selectByPrimaryKey(Integer maintenanceId);

    int updateByExampleSelective(@Param("record") EamMaintenance record, @Param("example") EamMaintenanceExample example);

    int updateByExample(@Param("record") EamMaintenance record, @Param("example") EamMaintenanceExample example);

    int updateByPrimaryKeySelective(EamMaintenance record);

    int updateByPrimaryKey(EamMaintenance record);

    void batchInsert(@Param("items") List<EamMaintenance> items);
}