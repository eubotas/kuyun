package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamProductLineDataElementMapper;
import com.kuyun.eam.dao.model.EamProductLineDataElement;
import com.kuyun.eam.dao.model.EamProductLineDataElementExample;
import com.kuyun.eam.rpc.api.EamProductLineDataElementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamProductLineDataElementService实现
* Created by kuyun on 2018/1/23.
*/
@Service
@Transactional
@BaseService
public class EamProductLineDataElementServiceImpl extends BaseServiceImpl<EamProductLineDataElementMapper, EamProductLineDataElement, EamProductLineDataElementExample> implements EamProductLineDataElementService {

    private static Logger _log = LoggerFactory.getLogger(EamProductLineDataElementServiceImpl.class);

    @Autowired
    EamProductLineDataElementMapper eamProductLineDataElementMapper;

}