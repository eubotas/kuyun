package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamDataElementGroupMapper;
import com.kuyun.eam.dao.model.EamDataElementGroup;
import com.kuyun.eam.dao.model.EamDataElementGroupExample;
import com.kuyun.eam.rpc.api.EamDataElementGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamDataElementGroupService实现
* Created by kuyun on 2018/1/23.
*/
@Service
@Transactional
@BaseService
public class EamDataElementGroupServiceImpl extends BaseServiceImpl<EamDataElementGroupMapper, EamDataElementGroup, EamDataElementGroupExample> implements EamDataElementGroupService {

    private static Logger _log = LoggerFactory.getLogger(EamDataElementGroupServiceImpl.class);

    @Autowired
    EamDataElementGroupMapper eamDataElementGroupMapper;

}