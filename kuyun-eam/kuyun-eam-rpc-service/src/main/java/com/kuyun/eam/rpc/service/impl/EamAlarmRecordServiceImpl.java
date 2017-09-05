package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmRecordMapper;
import com.kuyun.eam.dao.model.EamAlarmRecord;
import com.kuyun.eam.dao.model.EamAlarmRecordExample;
import com.kuyun.eam.rpc.api.EamAlarmRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmRecordService实现
* Created by kuyun on 2017/9/5.
*/
@Service
@Transactional
@BaseService
public class EamAlarmRecordServiceImpl extends BaseServiceImpl<EamAlarmRecordMapper, EamAlarmRecord, EamAlarmRecordExample> implements EamAlarmRecordService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmRecordServiceImpl.class);

    @Autowired
    EamAlarmRecordMapper eamAlarmRecordMapper;

}