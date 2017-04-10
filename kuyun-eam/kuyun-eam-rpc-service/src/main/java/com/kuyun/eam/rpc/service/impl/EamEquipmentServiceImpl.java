package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentMapper;
import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentExample;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentService实现
* Created by kuyun on 2017/4/8.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentServiceImpl extends BaseServiceImpl<EamEquipmentMapper, EamEquipment, EamEquipmentExample> implements EamEquipmentService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentServiceImpl.class);

    @Autowired
    EamEquipmentMapper eamEquipmentMapper;

}