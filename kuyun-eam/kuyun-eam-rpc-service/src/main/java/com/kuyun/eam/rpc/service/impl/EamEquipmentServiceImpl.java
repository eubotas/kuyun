package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.common.db.DataSourceEnum;
import com.kuyun.common.db.DynamicDataSource;
import com.kuyun.common.util.CommonUtil;
import com.kuyun.eam.dao.mapper.EamEquipmentMapper;
import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentExample;
import com.kuyun.eam.rpc.api.EamEquipmentService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
* EamEquipmentService实现
* Created by kuyun on 2017/4/8.
*/
@Service
@Transactional
@BaseService
public class EamEquipmentServiceImpl extends BaseServiceImpl<EamEquipmentMapper, EamEquipment, EamEquipmentExample> implements EamEquipmentService {

    private static Logger _log = LoggerFactory.getLogger(EamEquipmentServiceImpl.class);

    @Autowired
    EamEquipmentMapper eamEquipmentMapper;

    @Override
    public int insert(EamEquipment record) {
        record.setEquipmentId(CommonUtil.generateRandomToken());
        return super.insert(record);
    }

    @Override
    public int insertSelective(EamEquipment record) {
        record.setEquipmentId(CommonUtil.generateRandomToken());
        return super.insertSelective(record);
    }

    public EamEquipment selectByPrimaryKey(String id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method selectByPrimaryKey = eamEquipmentMapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
            Object result = selectByPrimaryKey.invoke(eamEquipmentMapper, id);
            return (EamEquipment) result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    public int deleteByPrimaryKey(String id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method deleteByPrimaryKey = eamEquipmentMapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object result = deleteByPrimaryKey.invoke(eamEquipmentMapper, id);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    @Override
    public int deleteByPrimaryKeys(String ids) {
        try {
            if (StringUtils.isBlank(ids)) {
                return 0;
            }
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            String[] idArray = ids.split("-");
            int count = 0;
            for (String id : idArray) {
                if (StringUtils.isBlank(id)) {
                    continue;
                }
                Method deleteByPrimaryKey = eamEquipmentMapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
                Object result = deleteByPrimaryKey.invoke(eamEquipmentMapper, id);
                count += Integer.parseInt(String.valueOf(result));
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

}