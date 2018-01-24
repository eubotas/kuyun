package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentDataGroupMapper;
import com.kuyun.eam.dao.model.EamEquipmentDataGroup;
import com.kuyun.eam.dao.model.EamEquipmentDataGroupExample;
import com.kuyun.eam.rpc.api.EamEquipmentDataGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentDataGroupService实现
* Created by kuyun on 2018/1/23.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentDataGroupServiceImpl extends BaseServiceImpl<EamEquipmentDataGroupMapper, EamEquipmentDataGroup, EamEquipmentDataGroupExample> implements EamEquipmentDataGroupService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentDataGroupServiceImpl.class);

    @Autowired
    EamEquipmentDataGroupMapper eamEquipmentDataGroupMapper;

}