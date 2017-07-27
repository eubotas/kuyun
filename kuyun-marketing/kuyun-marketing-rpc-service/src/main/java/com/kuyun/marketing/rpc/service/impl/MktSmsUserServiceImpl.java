package com.kuyun.marketing.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.marketing.dao.mapper.MktSmsUserMapper;
import com.kuyun.marketing.dao.model.MktSmsUser;
import com.kuyun.marketing.dao.model.MktSmsUserExample;
import com.kuyun.marketing.rpc.api.MktSmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* MktSmsUserService实现
* Created by kuyun on 2017/7/22.
*/
@Service
@Transactional
@BaseService
public class MktSmsUserServiceImpl extends BaseServiceImpl<MktSmsUserMapper, MktSmsUser, MktSmsUserExample> implements MktSmsUserService {

    private static Logger _log = LoggerFactory.getLogger(MktSmsUserServiceImpl.class);

    @Autowired
    MktSmsUserMapper mktSmsUserMapper;

}