package com.kuyun.marketing.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.marketing.dao.mapper.MktSmsTemplateMapper;
import com.kuyun.marketing.dao.model.MktSmsTemplate;
import com.kuyun.marketing.dao.model.MktSmsTemplateExample;
import com.kuyun.marketing.rpc.api.MktSmsTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* MktSmsTemplateService实现
* Created by kuyun on 2017/7/22.
*/
@Service
@Transactional
@BaseService
public class MktSmsTemplateServiceImpl extends BaseServiceImpl<MktSmsTemplateMapper, MktSmsTemplate, MktSmsTemplateExample> implements MktSmsTemplateService {

    private static Logger _log = LoggerFactory.getLogger(MktSmsTemplateServiceImpl.class);

    @Autowired
    MktSmsTemplateMapper mktSmsTemplateMapper;

}