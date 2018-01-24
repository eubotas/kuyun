package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamProductLinePropertyMapper;
import com.kuyun.eam.dao.model.EamProductLineProperty;
import com.kuyun.eam.dao.model.EamProductLinePropertyExample;
import com.kuyun.eam.rpc.api.EamProductLinePropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamProductLinePropertyService实现
* Created by kuyun on 2018/1/21.
*/
@Service
@Transactional
@BaseService
public class EamProductLinePropertyServiceImpl extends BaseServiceImpl<EamProductLinePropertyMapper, EamProductLineProperty, EamProductLinePropertyExample> implements EamProductLinePropertyService {

    private static Logger _log = LoggerFactory.getLogger(EamProductLinePropertyServiceImpl.class);

    @Autowired
    EamProductLinePropertyMapper eamProductLinePropertyMapper;

}