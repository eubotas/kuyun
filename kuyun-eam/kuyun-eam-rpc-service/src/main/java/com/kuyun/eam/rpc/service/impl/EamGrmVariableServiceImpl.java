package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableMapper;
import com.kuyun.eam.dao.model.EamGrmVariable;
import com.kuyun.eam.dao.model.EamGrmVariableExample;
import com.kuyun.eam.rpc.api.EamGrmVariableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableService实现
* Created by kuyun on 2018/1/10.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableServiceImpl extends BaseServiceImpl<EamGrmVariableMapper, EamGrmVariable, EamGrmVariableExample> implements EamGrmVariableService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableServiceImpl.class);

    @Autowired
    EamGrmVariableMapper eamGrmVariableMapper;

}