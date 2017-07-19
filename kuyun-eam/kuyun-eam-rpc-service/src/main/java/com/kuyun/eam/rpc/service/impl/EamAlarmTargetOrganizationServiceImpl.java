package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmTargetOrganizationMapper;
import com.kuyun.eam.dao.model.EamAlarmTargetOrganization;
import com.kuyun.eam.dao.model.EamAlarmTargetOrganizationExample;
import com.kuyun.eam.rpc.api.EamAlarmTargetOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmTargetOrganizationService实现
* Created by kuyun on 2017/7/19.
*/
@Service
@Transactional
@BaseService
public class EamAlarmTargetOrganizationServiceImpl extends BaseServiceImpl<EamAlarmTargetOrganizationMapper, EamAlarmTargetOrganization, EamAlarmTargetOrganizationExample> implements EamAlarmTargetOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmTargetOrganizationServiceImpl.class);

    @Autowired
    EamAlarmTargetOrganizationMapper eamAlarmTargetOrganizationMapper;

}