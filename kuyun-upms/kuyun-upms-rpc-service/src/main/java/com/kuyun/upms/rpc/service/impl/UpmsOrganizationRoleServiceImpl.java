package com.kuyun.upms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.upms.dao.mapper.UpmsOrganizationRoleMapper;
import com.kuyun.upms.dao.model.UpmsOrganizationRole;
import com.kuyun.upms.dao.model.UpmsOrganizationRoleExample;
import com.kuyun.upms.rpc.api.UpmsOrganizationRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsOrganizationRoleService实现
* Created by kuyun on 2017/9/23.
*/
@Service
@Transactional
@BaseService
public class UpmsOrganizationRoleServiceImpl extends BaseServiceImpl<UpmsOrganizationRoleMapper, UpmsOrganizationRole, UpmsOrganizationRoleExample> implements UpmsOrganizationRoleService {

    private static Logger _log = LoggerFactory.getLogger(UpmsOrganizationRoleServiceImpl.class);

    @Autowired
    UpmsOrganizationRoleMapper upmsOrganizationRoleMapper;

}