package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableDataHistoryMapper;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistory;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistoryExample;
import com.kuyun.eam.rpc.api.EamGrmVariableDataHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableDataHistoryService实现
* Created by kuyun on 2018/1/6.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableDataHistoryServiceImpl extends BaseServiceImpl<EamGrmVariableDataHistoryMapper, EamGrmVariableDataHistory, EamGrmVariableDataHistoryExample> implements EamGrmVariableDataHistoryService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataHistoryServiceImpl.class);

    @Autowired
    EamGrmVariableDataHistoryMapper eamGrmVariableDataHistoryMapper;

}