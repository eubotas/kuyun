package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.common.db.DataSourceEnum;
import com.kuyun.common.db.DynamicDataSource;
import com.kuyun.common.util.CommonUtil;
import com.kuyun.eam.dao.mapper.EamGrmMapper;
import com.kuyun.eam.dao.mapper.EamGrmMapper;
import com.kuyun.eam.dao.model.EamGrm;
import com.kuyun.eam.dao.model.EamGrm;
import com.kuyun.eam.dao.model.EamGrmExample;
import com.kuyun.eam.rpc.api.EamGrmService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
* EamGrmService实现
* Created by kuyun on 2018/4/17.
*/
@Service
@Transactional
@BaseService
public class EamGrmServiceImpl extends BaseServiceImpl<EamGrmMapper, EamGrm, EamGrmExample> implements EamGrmService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmServiceImpl.class);

    @Autowired
    EamGrmMapper eamGrmMapper;

    @Override
    public int insert(EamGrm record) {
        record.setGrmId(CommonUtil.generateRandomToken());
        return super.insert(record);
    }

    @Override
    public int insertSelective(EamGrm record) {
        record.setGrmId(CommonUtil.generateRandomToken());
        return super.insertSelective(record);
    }

    @Override
    public EamGrm selectByPrimaryKey(String id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method selectByPrimaryKey = eamGrmMapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
            Object result = selectByPrimaryKey.invoke(eamGrmMapper, id);
            return (EamGrm) result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return null;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            //delete Grm
            Method deleteByPrimaryKey = eamGrmMapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object result = deleteByPrimaryKey.invoke(eamGrmMapper, id);

            //
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
                Method deleteByPrimaryKey = eamGrmMapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
                Object result = deleteByPrimaryKey.invoke(eamGrmMapper, id);
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