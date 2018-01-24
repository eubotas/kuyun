package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentCompanyMapper;
import com.kuyun.eam.dao.model.EamEquipmentCompany;
import com.kuyun.eam.dao.model.EamEquipmentCompanyExample;
import com.kuyun.eam.rpc.api.EamEquipmentCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentCompanyService实现
* Created by kuyun on 2017/9/27.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentCompanyServiceImpl extends BaseServiceImpl<EamEquipmentCompanyMapper, EamEquipmentCompany, EamEquipmentCompanyExample> implements EamEquipmentCompanyService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentCompanyServiceImpl.class);

    @Autowired
    EamEquipmentCompanyMapper eamEquipmentCompanyMapper;

}