package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamProtocolMapper;
import com.kuyun.eam.dao.model.EamProtocol;
import com.kuyun.eam.dao.model.EamProtocolExample;
import com.kuyun.eam.rpc.api.EamProtocolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamProtocolService实现
* Created by kuyun on 2017/6/12.
*/
@Service
@Transactional
@BaseService
public class EamProtocolServiceImpl extends BaseServiceImpl<EamProtocolMapper, EamProtocol, EamProtocolExample> implements EamProtocolService {

    private static Logger _log = LoggerFactory.getLogger(EamProtocolServiceImpl.class);

    @Autowired
    EamProtocolMapper eamProtocolMapper;

}