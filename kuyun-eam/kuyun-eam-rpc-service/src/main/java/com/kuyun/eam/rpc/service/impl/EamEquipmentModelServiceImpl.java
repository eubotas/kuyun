package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamEquipmentModelMapper;
import com.kuyun.eam.dao.mapper.EamEquipmentModelPropertiesMapper;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.EamEquipmentModelService;
import com.kuyun.upms.dao.model.UpmsOrganization;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.rpc.api.UpmsApiService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Date;

/**
* EamEquipmentModelService实现
* Created by kuyun on 2017/4/8.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentModelServiceImpl extends BaseServiceImpl<EamEquipmentModelMapper, EamEquipmentModel, EamEquipmentModelExample> implements EamEquipmentModelService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentModelServiceImpl.class);

    @Autowired
    EamEquipmentModelMapper eamEquipmentModelMapper;

    @Autowired
    EamEquipmentModelPropertiesMapper eamEquipmentModelPropertiesMapper;

    @Override
    public int insert(EamEquipmentModel record) {
        record.setDeleteFlag(Boolean.FALSE);
        return super.insert(record);
    }

    @Override
    public int insertSelective(EamEquipmentModel record) {
        record.setDeleteFlag(Boolean.FALSE);
        return super.insertSelective(record);
    }

    @Override
    public int deleteByPrimaryKeys(String ids) {
        String[] idArray = ids.split("-");
        int count = 0;
        for (String idStr : idArray) {
            if (StringUtils.isBlank(idStr)) {
                continue;
            }
            Integer id = Integer.parseInt(idStr);
            count += deleteByPrimaryKey(id);
        }
        return count;
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        EamEquipmentModelPropertiesExample example = new EamEquipmentModelPropertiesExample();
        example.createCriteria().andEquipmentModelIdEqualTo(id);
        eamEquipmentModelPropertiesMapper.deleteByExample(example);
        return super.deleteByPrimaryKey(id);
    }

}