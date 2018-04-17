package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmEquipmentMapper;
import com.kuyun.eam.dao.model.EamGrmEquipment;
import com.kuyun.eam.dao.model.EamGrmEquipmentExample;
import com.kuyun.eam.rpc.api.EamGrmEquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmEquipmentService实现
* Created by kuyun on 2018/4/17.
*/
@Service
@Transactional
@BaseService
public class EamGrmEquipmentServiceImpl extends BaseServiceImpl<EamGrmEquipmentMapper, EamGrmEquipment, EamGrmEquipmentExample> implements EamGrmEquipmentService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmEquipmentServiceImpl.class);

    @Autowired
    EamGrmEquipmentMapper eamGrmEquipmentMapper;

}