package com.kuyun.upms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.upms.dao.mapper.UpmsCompanyMapper;
import com.kuyun.upms.dao.model.UpmsCompany;
import com.kuyun.upms.dao.model.UpmsCompanyExample;
import com.kuyun.upms.rpc.api.UpmsCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsCompanyService实现
* Created by kuyun on 2017/9/22.
*/
@Service
@Transactional
@BaseService
public class UpmsCompanyServiceImpl extends BaseServiceImpl<UpmsCompanyMapper, UpmsCompany, UpmsCompanyExample> implements UpmsCompanyService {

    private static Logger _log = LoggerFactory.getLogger(UpmsCompanyServiceImpl.class);

    @Autowired
    UpmsCompanyMapper upmsCompanyMapper;

}