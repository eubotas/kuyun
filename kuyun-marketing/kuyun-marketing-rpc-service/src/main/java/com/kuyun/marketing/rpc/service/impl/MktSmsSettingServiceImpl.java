package com.kuyun.marketing.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.marketing.dao.mapper.MktSmsSettingMapper;
import com.kuyun.marketing.dao.model.MktSmsSetting;
import com.kuyun.marketing.dao.model.MktSmsSettingExample;
import com.kuyun.marketing.rpc.api.MktSmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* MktSmsSettingService实现
* Created by kuyun on 2017/7/22.
*/
@Service
@Transactional
@BaseService
public class MktSmsSettingServiceImpl extends BaseServiceImpl<MktSmsSettingMapper, MktSmsSetting, MktSmsSettingExample> implements MktSmsSettingService {

    private static Logger _log = LoggerFactory.getLogger(MktSmsSettingServiceImpl.class);

    @Autowired
    MktSmsSettingMapper mktSmsSettingMapper;

}