package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamSensorWriteDataMapper;
import com.kuyun.eam.dao.model.EamSensorWriteData;
import com.kuyun.eam.dao.model.EamSensorWriteDataExample;
import com.kuyun.eam.rpc.api.EamSensorWriteDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamSensorWriteDataService实现
* Created by kuyun on 2017/10/18.
*/
@Service
@Transactional
@BaseService
public class EamSensorWriteDataServiceImpl extends BaseServiceImpl<EamSensorWriteDataMapper, EamSensorWriteData, EamSensorWriteDataExample> implements EamSensorWriteDataService {

    private static Logger _log = LoggerFactory.getLogger(EamSensorWriteDataServiceImpl.class);

    @Autowired
    EamSensorWriteDataMapper eamSensorWriteDataMapper;

}