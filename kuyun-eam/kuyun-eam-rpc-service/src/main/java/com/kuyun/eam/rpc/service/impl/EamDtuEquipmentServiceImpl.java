package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamDtuEquipmentMapper;
import com.kuyun.eam.dao.model.EamDtuEquipment;
import com.kuyun.eam.dao.model.EamDtuEquipmentExample;
import com.kuyun.eam.rpc.api.EamDtuEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamDtuEquipmentService实现
* Created by kuyun on 2017/11/4.
*/
@Service
@Transactional
@BaseService
public class EamDtuEquipmentServiceImpl extends BaseServiceImpl<EamDtuEquipmentMapper, EamDtuEquipment, EamDtuEquipmentExample> implements EamDtuEquipmentService {

    private static Logger _log = LoggerFactory.getLogger(EamDtuEquipmentServiceImpl.class);

    @Autowired
    EamDtuEquipmentMapper eamDtuEquipmentMapper;

}