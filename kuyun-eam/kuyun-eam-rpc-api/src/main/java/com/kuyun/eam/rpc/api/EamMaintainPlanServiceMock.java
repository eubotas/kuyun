package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamMaintainPlanMapper;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;

/**
* 降级实现EamMaintainPlanService接口
* Created by kuyun on 2018/2/23.
*/
public class EamMaintainPlanServiceMock extends BaseServiceMock<EamMaintainPlanMapper, EamMaintainPlan, EamMaintainPlanExample> implements EamMaintainPlanService {

    @Override
    public int createMaintainPlan(EamMaintainPlan plan, String[] maintainUserIds) {
        return 0;
    }

    @Override
    public int deleteMaintainPlan(String ids) {
        return 0;
    }

    @Override
    public int updateMaintainPlan(EamMaintainPlan plan, String[] maintainUserIds) {
        return 0;
    }
}
