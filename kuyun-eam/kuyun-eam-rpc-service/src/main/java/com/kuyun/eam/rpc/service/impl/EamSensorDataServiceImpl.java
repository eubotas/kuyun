package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamSensorDataMapper;
import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.dao.model.EamSensorDataExample;
import com.kuyun.eam.rpc.api.EamSensorDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamSensorDataService实现
* Created by kuyun on 2017/6/12.
*/
@Service
@Transactional
@BaseService
public class EamSensorDataServiceImpl extends BaseServiceImpl<EamSensorDataMapper, EamSensorData, EamSensorDataExample> implements EamSensorDataService {

    private static Logger _log = LoggerFactory.getLogger(EamSensorDataServiceImpl.class);

    @Autowired
    EamSensorDataMapper eamSensorDataMapper;

}