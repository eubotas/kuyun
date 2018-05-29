package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableGroupMapper;
import com.kuyun.eam.dao.model.EamGrmVariableGroup;
import com.kuyun.eam.dao.model.EamGrmVariableGroupExample;
import com.kuyun.eam.rpc.api.EamGrmVariableGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableGroupService实现
* Created by kuyun on 2018/5/29.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableGroupServiceImpl extends BaseServiceImpl<EamGrmVariableGroupMapper, EamGrmVariableGroup, EamGrmVariableGroupExample> implements EamGrmVariableGroupService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableGroupServiceImpl.class);

    @Autowired
    EamGrmVariableGroupMapper eamGrmVariableGroupMapper;

}