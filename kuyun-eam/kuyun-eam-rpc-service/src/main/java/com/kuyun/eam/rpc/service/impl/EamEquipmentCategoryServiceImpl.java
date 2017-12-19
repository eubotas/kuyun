package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentCategoryMapper;
import com.kuyun.eam.dao.model.EamEquipmentCategory;
import com.kuyun.eam.dao.model.EamEquipmentCategoryExample;
import com.kuyun.eam.rpc.api.EamEquipmentCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentCategoryService实现
* Created by kuyun on 2017/12/14.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentCategoryServiceImpl extends BaseServiceImpl<EamEquipmentCategoryMapper, EamEquipmentCategory, EamEquipmentCategoryExample> implements EamEquipmentCategoryService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentCategoryServiceImpl.class);

    @Autowired
    EamEquipmentCategoryMapper eamEquipmentCategoryMapper;

}