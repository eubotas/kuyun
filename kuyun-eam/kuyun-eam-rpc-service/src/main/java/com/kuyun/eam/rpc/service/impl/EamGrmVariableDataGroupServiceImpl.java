package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableDataGroupMapper;
import com.kuyun.eam.dao.model.EamGrmVariableDataGroup;
import com.kuyun.eam.dao.model.EamGrmVariableDataGroupExample;
import com.kuyun.eam.rpc.api.EamGrmVariableDataGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableDataGroupService实现
* Created by kuyun on 2018/5/29.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableDataGroupServiceImpl extends BaseServiceImpl<EamGrmVariableDataGroupMapper, EamGrmVariableDataGroup, EamGrmVariableDataGroupExample> implements EamGrmVariableDataGroupService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataGroupServiceImpl.class);

    @Autowired
    EamGrmVariableDataGroupMapper eamGrmVariableDataGroupMapper;

}