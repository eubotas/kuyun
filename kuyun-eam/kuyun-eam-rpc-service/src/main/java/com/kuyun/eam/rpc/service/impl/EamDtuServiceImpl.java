package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.common.db.DataSourceEnum;
import com.kuyun.common.db.DynamicDataSource;
import com.kuyun.common.util.CommonUtil;
import com.kuyun.eam.dao.mapper.EamDtuMapper;
import com.kuyun.eam.dao.mapper.EamDtuMapper;
import com.kuyun.eam.dao.model.EamDtu;
import com.kuyun.eam.dao.model.EamDtuExample;
import com.kuyun.eam.dao.model.EamDtu;
import com.kuyun.eam.rpc.api.EamDtuService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

/**
* EamDtuService实现
* Created by kuyun on 2017/11/4.
*/
@Service
@Transactional
@BaseService
public class EamDtuServiceImpl extends BaseServiceImpl<EamDtuMapper, EamDtu, EamDtuExample> implements EamDtuService {
    private static Logger _log = LoggerFactory.getLogger(EamDtuServiceImpl.class);

    @Autowired
    EamDtuMapper EamDtuMapper;

    @Override
    public int insert(EamDtu record) {
        record.setDtuId(CommonUtil.generateRandomToken());
        return super.insert(record);
    }

    @Override
    public int insertSelective(EamDtu record) {
        record.setDtuId(CommonUtil.generateRandomToken());
        return super.insertSelective(record);
    }

    @Override
    public EamDtu selectByPrimaryKey(String id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method selectByPrimaryKey = EamDtuMapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
            Object result = selectByPrimaryKey.invoke(EamDtuMapper, id);
            return (EamDtu) result;
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
            //delete Dtu
            Method deleteByPrimaryKey = EamDtuMapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object result = deleteByPrimaryKey.invoke(EamDtuMapper, id);

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
                Method deleteByPrimaryKey = EamDtuMapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
                Object result = deleteByPrimaryKey.invoke(EamDtuMapper, id);
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