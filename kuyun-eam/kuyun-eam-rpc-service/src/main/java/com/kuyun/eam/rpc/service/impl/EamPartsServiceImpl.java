package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamPartsMapper;
import com.kuyun.eam.dao.model.EamParts;
import com.kuyun.eam.dao.model.EamParts;
import com.kuyun.eam.dao.model.EamPartsExample;
import com.kuyun.eam.rpc.api.EamPartsService;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
* EamPartsService实现
* Created by kuyun on 2017/4/20.
*/
@Service
@Transactional
@BaseService
public class EamPartsServiceImpl extends BaseServiceImpl<EamPartsMapper, EamParts, EamPartsExample> implements EamPartsService {

    private static Logger _log = LoggerFactory.getLogger(EamPartsServiceImpl.class);

    @Autowired
    EamPartsMapper eamPartsMapper;

}