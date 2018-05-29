package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableDataHistoryGroupMapper;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistoryGroup;
import com.kuyun.eam.dao.model.EamGrmVariableDataHistoryGroupExample;
import com.kuyun.eam.rpc.api.EamGrmVariableDataHistoryGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableDataHistoryGroupService实现
* Created by kuyun on 2018/5/29.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableDataHistoryGroupServiceImpl extends BaseServiceImpl<EamGrmVariableDataHistoryGroupMapper, EamGrmVariableDataHistoryGroup, EamGrmVariableDataHistoryGroupExample> implements EamGrmVariableDataHistoryGroupService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataHistoryGroupServiceImpl.class);

    @Autowired
    EamGrmVariableDataHistoryGroupMapper eamGrmVariableDataHistoryGroupMapper;

}