package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmMapper;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamAlarmExample;
import com.kuyun.eam.rpc.api.EamAlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmService实现
* Created by kuyun on 2017/6/30.
*/
@Service
@Transactional
@BaseService
public class EamAlarmServiceImpl extends BaseServiceImpl<EamAlarmMapper, EamAlarm, EamAlarmExample> implements EamAlarmService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmServiceImpl.class);

    @Autowired
    EamAlarmMapper eamAlarmMapper;

}