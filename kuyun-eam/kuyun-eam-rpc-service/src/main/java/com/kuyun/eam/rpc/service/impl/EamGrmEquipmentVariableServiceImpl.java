package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmEquipmentVariableMapper;
import com.kuyun.eam.dao.model.EamGrmEquipmentVariable;
import com.kuyun.eam.dao.model.EamGrmEquipmentVariableExample;
import com.kuyun.eam.rpc.api.EamGrmEquipmentVariableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmEquipmentVariableService实现
* Created by kuyun on 2018/1/1.
*/
@Service
@Transactional
@BaseService
public class EamGrmEquipmentVariableServiceImpl extends BaseServiceImpl<EamGrmEquipmentVariableMapper, EamGrmEquipmentVariable, EamGrmEquipmentVariableExample> implements EamGrmEquipmentVariableService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmEquipmentVariableServiceImpl.class);

    @Autowired
    EamGrmEquipmentVariableMapper eamGrmEquipmentVariableMapper;

}