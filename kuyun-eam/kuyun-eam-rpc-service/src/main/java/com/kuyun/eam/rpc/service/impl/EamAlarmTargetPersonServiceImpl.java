package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmTargetPersonMapper;
import com.kuyun.eam.dao.model.EamAlarmTargetPerson;
import com.kuyun.eam.dao.model.EamAlarmTargetPersonExample;
import com.kuyun.eam.rpc.api.EamAlarmTargetPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmTargetPersonService实现
* Created by kuyun on 2017/6/30.
*/
@Service
@Transactional
@BaseService
public class EamAlarmTargetPersonServiceImpl extends BaseServiceImpl<EamAlarmTargetPersonMapper, EamAlarmTargetPerson, EamAlarmTargetPersonExample> implements EamAlarmTargetPersonService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmTargetPersonServiceImpl.class);

    @Autowired
    EamAlarmTargetPersonMapper eamAlarmTargetPersonMapper;

}