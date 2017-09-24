package com.kuyun.upms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.upms.dao.mapper.UpmsUserCompanyMapper;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.model.UpmsUserCompanyExample;
import com.kuyun.upms.rpc.api.UpmsUserCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsUserCompanyService实现
* Created by kuyun on 2017/9/23.
*/
@Service
@Transactional
@BaseService
public class UpmsUserCompanyServiceImpl extends BaseServiceImpl<UpmsUserCompanyMapper, UpmsUserCompany, UpmsUserCompanyExample> implements UpmsUserCompanyService {

    private static Logger _log = LoggerFactory.getLogger(UpmsUserCompanyServiceImpl.class);

    @Autowired
    UpmsUserCompanyMapper upmsUserCompanyMapper;

}