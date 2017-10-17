package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamSensorDataHistoryMapper;
import com.kuyun.eam.dao.model.EamSensorDataHistory;
import com.kuyun.eam.dao.model.EamSensorDataHistoryExample;
import com.kuyun.eam.rpc.api.EamSensorDataHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamSensorDataHistoryService实现
* Created by kuyun on 2017/10/13.
*/
@Service
@Transactional
@BaseService
public class EamSensorDataHistoryServiceImpl extends BaseServiceImpl<EamSensorDataHistoryMapper, EamSensorDataHistory, EamSensorDataHistoryExample> implements EamSensorDataHistoryService {

    private static Logger _log = LoggerFactory.getLogger(EamSensorDataHistoryServiceImpl.class);

    @Autowired
    EamSensorDataHistoryMapper eamSensorDataHistoryMapper;

}