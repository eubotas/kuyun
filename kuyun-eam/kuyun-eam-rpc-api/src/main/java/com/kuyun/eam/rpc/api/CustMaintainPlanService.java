package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;

/**
* EamMaintainPlanService接口
* Created by kuyun on 2018/1/24.
*/
public interface CustMaintainPlanService extends BaseService<EamMaintainPlan, EamMaintainPlanExample> {

    public int createMaintainPlan(EamMaintainPlan plan);

    public int deleteMaintainPlan(String ids);

    public int updateMaintainPlan(EamMaintainPlan plan);

}