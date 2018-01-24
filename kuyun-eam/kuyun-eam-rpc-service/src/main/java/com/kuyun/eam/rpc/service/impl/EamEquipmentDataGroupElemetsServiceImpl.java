package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentDataGroupElemetsMapper;
import com.kuyun.eam.dao.model.EamEquipmentDataGroupElemets;
import com.kuyun.eam.dao.model.EamEquipmentDataGroupElemetsExample;
import com.kuyun.eam.rpc.api.EamEquipmentDataGroupElemetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentDataGroupElemetsService实现
* Created by kuyun on 2018/1/23.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentDataGroupElemetsServiceImpl extends BaseServiceImpl<EamEquipmentDataGroupElemetsMapper, EamEquipmentDataGroupElemets, EamEquipmentDataGroupElemetsExample> implements EamEquipmentDataGroupElemetsService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentDataGroupElemetsServiceImpl.class);

    @Autowired
    EamEquipmentDataGroupElemetsMapper eamEquipmentDataGroupElemetsMapper;

}