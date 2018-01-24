package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamMaintenanceMapper;
import com.kuyun.eam.dao.model.EamMaintenance;
import com.kuyun.eam.dao.model.EamMaintenance;
import com.kuyun.eam.dao.model.EamMaintenanceExample;
import com.kuyun.eam.rpc.api.EamMaintenanceService;
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
* EamMaintenanceService实现
* Created by kuyun on 2017/4/20.
*/
@Service
@Transactional
@BaseService
public class EamMaintenanceServiceImpl extends BaseServiceImpl<EamMaintenanceMapper, EamMaintenance, EamMaintenanceExample> implements EamMaintenanceService {

    private static Logger _log = LoggerFactory.getLogger(EamMaintenanceServiceImpl.class);

    @Autowired
    EamMaintenanceMapper eamMaintenanceMapper;
}