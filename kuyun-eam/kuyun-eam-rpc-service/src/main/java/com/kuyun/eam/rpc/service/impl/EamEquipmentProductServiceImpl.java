package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentProductMapper;
import com.kuyun.eam.dao.model.EamEquipmentProduct;
import com.kuyun.eam.dao.model.EamEquipmentProductExample;
import com.kuyun.eam.rpc.api.EamEquipmentProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentProductService实现
* Created by kuyun on 2018/5/31.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentProductServiceImpl extends BaseServiceImpl<EamEquipmentProductMapper, EamEquipmentProduct, EamEquipmentProductExample> implements EamEquipmentProductService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentProductServiceImpl.class);

    @Autowired
    EamEquipmentProductMapper eamEquipmentProductMapper;

}