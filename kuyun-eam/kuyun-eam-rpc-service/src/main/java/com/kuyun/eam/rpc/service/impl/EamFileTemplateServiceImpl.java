package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamFileTemplateMapper;
import com.kuyun.eam.dao.model.EamFileTemplate;
import com.kuyun.eam.dao.model.EamFileTemplateExample;
import com.kuyun.eam.rpc.api.EamFileTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamFileTemplateService实现
* Created by kuyun on 2018/4/13.
*/
@Service
@Transactional
@BaseService
public class EamFileTemplateServiceImpl extends BaseServiceImpl<EamFileTemplateMapper, EamFileTemplate, EamFileTemplateExample> implements EamFileTemplateService {

    private static Logger _log = LoggerFactory.getLogger(EamFileTemplateServiceImpl.class);

    @Autowired
    EamFileTemplateMapper eamFileTemplateMapper;

}