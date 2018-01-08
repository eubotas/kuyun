package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableDataMapper;
import com.kuyun.eam.dao.model.EamGrmVariableData;
import com.kuyun.eam.dao.model.EamGrmVariableDataExample;
import com.kuyun.eam.rpc.api.EamGrmVariableDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableDataService实现
* Created by kuyun on 2018/1/6.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableDataServiceImpl extends BaseServiceImpl<EamGrmVariableDataMapper, EamGrmVariableData, EamGrmVariableDataExample> implements EamGrmVariableDataService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataServiceImpl.class);

    @Autowired
    EamGrmVariableDataMapper eamGrmVariableDataMapper;

}