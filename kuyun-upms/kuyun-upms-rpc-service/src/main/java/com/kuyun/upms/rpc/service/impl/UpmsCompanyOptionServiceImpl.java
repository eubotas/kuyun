package com.kuyun.upms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.upms.dao.mapper.UpmsCompanyOptionMapper;
import com.kuyun.upms.dao.model.UpmsCompanyOption;
import com.kuyun.upms.dao.model.UpmsCompanyOptionExample;
import com.kuyun.upms.rpc.api.UpmsCompanyOptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsCompanyOptionService实现
* Created by kuyun on 2018/5/16.
*/
@Service
@Transactional
@BaseService
public class UpmsCompanyOptionServiceImpl extends BaseServiceImpl<UpmsCompanyOptionMapper, UpmsCompanyOption, UpmsCompanyOptionExample> implements UpmsCompanyOptionService {

    private static Logger _log = LoggerFactory.getLogger(UpmsCompanyOptionServiceImpl.class);

    @Autowired
    UpmsCompanyOptionMapper upmsCompanyOptionMapper;

}