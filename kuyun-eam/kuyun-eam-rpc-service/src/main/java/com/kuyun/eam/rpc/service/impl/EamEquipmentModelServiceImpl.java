package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentModelMapper;
import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentModel;
import com.kuyun.eam.dao.model.EamEquipmentModelExample;
import com.kuyun.eam.rpc.api.EamEquipmentModelService;
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
* EamEquipmentModelService实现
* Created by kuyun on 2017/4/8.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentModelServiceImpl extends BaseServiceImpl<EamEquipmentModelMapper, EamEquipmentModel, EamEquipmentModelExample> implements EamEquipmentModelService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentModelServiceImpl.class);

    @Autowired
    EamEquipmentModelMapper eamEquipmentModelMapper;

}