package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamSensorWriteDataHistoryMapper;
import com.kuyun.eam.dao.model.EamSensorWriteDataHistory;
import com.kuyun.eam.dao.model.EamSensorWriteDataHistoryExample;
import com.kuyun.eam.rpc.api.EamSensorWriteDataHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamSensorWriteDataHistoryService实现
* Created by kuyun on 2017/10/18.
*/
@Service
@Transactional
@BaseService
public class EamSensorWriteDataHistoryServiceImpl extends BaseServiceImpl<EamSensorWriteDataHistoryMapper, EamSensorWriteDataHistory, EamSensorWriteDataHistoryExample> implements EamSensorWriteDataHistoryService {

    private static Logger _log = LoggerFactory.getLogger(EamSensorWriteDataHistoryServiceImpl.class);

    @Autowired
    EamSensorWriteDataHistoryMapper eamSensorWriteDataHistoryMapper;

}