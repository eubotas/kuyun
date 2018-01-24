package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmTargetUserMapper;
import com.kuyun.eam.dao.model.EamAlarmTargetUser;
import com.kuyun.eam.dao.model.EamAlarmTargetUserExample;
import com.kuyun.eam.rpc.api.EamAlarmTargetUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmTargetUserService实现
* Created by kuyun on 2017/8/1.
*/
@Service
@Transactional
@BaseService
public class EamAlarmTargetUserServiceImpl extends BaseServiceImpl<EamAlarmTargetUserMapper, EamAlarmTargetUser, EamAlarmTargetUserExample> implements EamAlarmTargetUserService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmTargetUserServiceImpl.class);

    @Autowired
    EamAlarmTargetUserMapper eamAlarmTargetUserMapper;

}