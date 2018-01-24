package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamSensorMapper;
import com.kuyun.eam.dao.model.EamSensor;
import com.kuyun.eam.dao.model.EamSensorExample;
import com.kuyun.eam.rpc.api.EamSensorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamSensorService实现
* Created by kuyun on 2017/6/12.
*/
@Service
@Transactional
@BaseService
public class EamSensorServiceImpl extends BaseServiceImpl<EamSensorMapper, EamSensor, EamSensorExample> implements EamSensorService {

    private static Logger _log = LoggerFactory.getLogger(EamSensorServiceImpl.class);

    @Autowired
    EamSensorMapper eamSensorMapper;

}