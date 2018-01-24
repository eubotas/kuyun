package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamDataElementMapper;
import com.kuyun.eam.dao.model.EamDataElement;
import com.kuyun.eam.dao.model.EamDataElementExample;
import com.kuyun.eam.rpc.api.EamDataElementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamDataElementService实现
* Created by kuyun on 2018/1/23.
*/
@Service
@Transactional
@BaseService
public class EamDataElementServiceImpl extends BaseServiceImpl<EamDataElementMapper, EamDataElement, EamDataElementExample> implements EamDataElementService {

    private static Logger _log = LoggerFactory.getLogger(EamDataElementServiceImpl.class);

    @Autowired
    EamDataElementMapper eamDataElementMapper;

}