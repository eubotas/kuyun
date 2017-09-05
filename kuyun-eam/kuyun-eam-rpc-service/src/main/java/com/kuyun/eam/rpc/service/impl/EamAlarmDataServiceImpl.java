package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmDataMapper;
import com.kuyun.eam.dao.model.EamAlarmData;
import com.kuyun.eam.dao.model.EamAlarmDataExample;
import com.kuyun.eam.rpc.api.EamAlarmDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmDataService实现
* Created by kuyun on 2017/9/5.
*/
@Service
@Transactional
@BaseService
public class EamAlarmDataServiceImpl extends BaseServiceImpl<EamAlarmDataMapper, EamAlarmData, EamAlarmDataExample> implements EamAlarmDataService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmDataServiceImpl.class);

    @Autowired
    EamAlarmDataMapper eamAlarmDataMapper;

}