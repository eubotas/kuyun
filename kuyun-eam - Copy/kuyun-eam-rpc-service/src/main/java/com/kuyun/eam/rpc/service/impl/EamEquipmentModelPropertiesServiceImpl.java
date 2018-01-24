package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentModelPropertiesMapper;
import com.kuyun.eam.dao.model.EamEquipmentModel;
import com.kuyun.eam.dao.model.EamEquipmentModelProperties;
import com.kuyun.eam.dao.model.EamEquipmentModelPropertiesExample;
import com.kuyun.eam.rpc.api.EamEquipmentModelPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamEquipmentModelPropertiesService实现
* Created by kuyun on 2017/4/8.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentModelPropertiesServiceImpl extends BaseServiceImpl<EamEquipmentModelPropertiesMapper, EamEquipmentModelProperties, EamEquipmentModelPropertiesExample> implements EamEquipmentModelPropertiesService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentModelPropertiesServiceImpl.class);

    @Autowired
    EamEquipmentModelPropertiesMapper eamEquipmentModelPropertiesMapper;


    @Override
    public int insert(EamEquipmentModelProperties record) {
        record.setDeleteFlag(Boolean.FALSE);
        return super.insert(record);
    }

    @Override
    public int insertSelective(EamEquipmentModelProperties record) {
        record.setDeleteFlag(Boolean.FALSE);
        return super.insertSelective(record);
    }

}