package com.kuyun.eam.dao.mapper;

import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EamMaintainPlanMapper {
    long countByExample(EamMaintainPlanExample example);

    int deleteByExample(EamMaintainPlanExample example);

    int deleteByPrimaryKey(Integer planId);

    int insert(EamMaintainPlan record);

    int insertSelective(EamMaintainPlan record);

    List<EamMaintainPlan> selectByExample(EamMaintainPlanExample example);

    EamMaintainPlan selectByPrimaryKey(Integer planId);

    int updateByExampleSelective(@Param("record") EamMaintainPlan record, @Param("example") EamMaintainPlanExample example);

    int updateByExample(@Param("record") EamMaintainPlan record, @Param("example") EamMaintainPlanExample example);

    int updateByPrimaryKeySelective(EamMaintainPlan record);

    int updateByPrimaryKey(EamMaintainPlan record);

    void batchInsert(@Param("items") List<EamMaintainPlan> items);
}