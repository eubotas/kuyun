package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamMaintainUserMapper;
import com.kuyun.eam.dao.model.EamMaintainUser;
import com.kuyun.eam.dao.model.EamMaintainUserExample;
import com.kuyun.eam.rpc.api.EamMaintainUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamMaintainUserService实现
* Created by kuyun on 2018/4/23.
*/
@Service
@Transactional
@BaseService
public class EamMaintainUserServiceImpl extends BaseServiceImpl<EamMaintainUserMapper, EamMaintainUser, EamMaintainUserExample> implements EamMaintainUserService {

    private static Logger _log = LoggerFactory.getLogger(EamMaintainUserServiceImpl.class);

    @Autowired
    EamMaintainUserMapper eamMaintainUserMapper;

}