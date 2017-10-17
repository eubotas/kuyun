package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmRecordHistoryMapper;
import com.kuyun.eam.dao.model.EamAlarmRecordHistory;
import com.kuyun.eam.dao.model.EamAlarmRecordHistoryExample;
import com.kuyun.eam.rpc.api.EamAlarmRecordHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmRecordHistoryService实现
* Created by kuyun on 2017/10/17.
*/
@Service
@Transactional
@BaseService
public class EamAlarmRecordHistoryServiceImpl extends BaseServiceImpl<EamAlarmRecordHistoryMapper, EamAlarmRecordHistory, EamAlarmRecordHistoryExample> implements EamAlarmRecordHistoryService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmRecordHistoryServiceImpl.class);

    @Autowired
    EamAlarmRecordHistoryMapper eamAlarmRecordHistoryMapper;

}