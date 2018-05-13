package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentPropertyMapper;
import com.kuyun.eam.dao.model.EamEquipmentProperty;
import com.kuyun.eam.dao.model.EamEquipmentPropertyExample;
import com.kuyun.eam.rpc.api.EamEquipmentPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentPropertyService实现
* Created by kuyun on 2018/5/12.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentPropertyServiceImpl extends BaseServiceImpl<EamEquipmentPropertyMapper, EamEquipmentProperty, EamEquipmentPropertyExample> implements EamEquipmentPropertyService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentPropertyServiceImpl.class);

    @Autowired
    EamEquipmentPropertyMapper eamEquipmentPropertyMapper;

}