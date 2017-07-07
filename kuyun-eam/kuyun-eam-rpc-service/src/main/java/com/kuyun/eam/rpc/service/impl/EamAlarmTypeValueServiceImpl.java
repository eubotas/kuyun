package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmTypeValueMapper;
import com.kuyun.eam.dao.model.EamAlarmTypeValue;
import com.kuyun.eam.dao.model.EamAlarmTypeValueExample;
import com.kuyun.eam.rpc.api.EamAlarmTypeValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmTypeValueService实现
* Created by kuyun on 2017/6/30.
*/
@Service
@Transactional
@BaseService
public class EamAlarmTypeValueServiceImpl extends BaseServiceImpl<EamAlarmTypeValueMapper, EamAlarmTypeValue, EamAlarmTypeValueExample> implements EamAlarmTypeValueService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmTypeValueServiceImpl.class);

    @Autowired
    EamAlarmTypeValueMapper eamAlarmTypeValueMapper;

}