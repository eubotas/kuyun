package com.kuyun.marketing.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.marketing.dao.mapper.MktSmsMapper;
import com.kuyun.marketing.dao.model.MktSms;
import com.kuyun.marketing.dao.model.MktSmsExample;
import com.kuyun.marketing.rpc.api.MktSmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* MktSmsService实现
* Created by kuyun on 2017/7/22.
*/
@Service
@Transactional
@BaseService
public class MktSmsServiceImpl extends BaseServiceImpl<MktSmsMapper, MktSms, MktSmsExample> implements MktSmsService {

    private static Logger _log = LoggerFactory.getLogger(MktSmsServiceImpl.class);

    @Autowired
    MktSmsMapper mktSmsMapper;

}