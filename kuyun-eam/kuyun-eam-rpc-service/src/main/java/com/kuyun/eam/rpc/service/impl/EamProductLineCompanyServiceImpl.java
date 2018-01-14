package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamProductLineCompanyMapper;
import com.kuyun.eam.dao.model.EamProductLineCompany;
import com.kuyun.eam.dao.model.EamProductLineCompanyExample;
import com.kuyun.eam.rpc.api.EamProductLineCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamProductLineCompanyService实现
* Created by kuyun on 2018/1/10.
*/
@Service
@Transactional
@BaseService
public class EamProductLineCompanyServiceImpl extends BaseServiceImpl<EamProductLineCompanyMapper, EamProductLineCompany, EamProductLineCompanyExample> implements EamProductLineCompanyService {

    private static Logger _log = LoggerFactory.getLogger(EamProductLineCompanyServiceImpl.class);

    @Autowired
    EamProductLineCompanyMapper eamProductLineCompanyMapper;

}