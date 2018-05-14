package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamWarehouseMapper;
import com.kuyun.eam.dao.model.EamWarehouse;
import com.kuyun.eam.dao.model.EamWarehouseExample;
import com.kuyun.eam.rpc.api.EamWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamWarehouseService实现
* Created by kuyun on 2018/5/14.
*/
@Service
@Transactional
@BaseService
public class EamWarehouseServiceImpl extends BaseServiceImpl<EamWarehouseMapper, EamWarehouse, EamWarehouseExample> implements EamWarehouseService {

    private static Logger _log = LoggerFactory.getLogger(EamWarehouseServiceImpl.class);

    @Autowired
    EamWarehouseMapper eamWarehouseMapper;

}