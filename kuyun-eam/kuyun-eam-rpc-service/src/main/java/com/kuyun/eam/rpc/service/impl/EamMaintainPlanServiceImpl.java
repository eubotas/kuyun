package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamMaintainPlanMapper;
import com.kuyun.eam.dao.model.EamMaintainPlan;
import com.kuyun.eam.dao.model.EamMaintainPlanExample;
import com.kuyun.eam.rpc.api.EamMaintainPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamMaintainPlanService实现
* Created by kuyun on 2018/1/24.
*/
@Service
@Transactional
@BaseService
public class EamMaintainPlanServiceImpl extends CustMaintainPlanServiceImpl implements EamMaintainPlanService {

    private static Logger _log = LoggerFactory.getLogger(EamMaintainPlanServiceImpl.class);

    @Autowired
    EamMaintainPlanMapper eamMaintainPlanMapper;

}