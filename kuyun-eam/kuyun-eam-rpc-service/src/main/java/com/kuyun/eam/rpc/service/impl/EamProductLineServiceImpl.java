package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.common.db.DataSourceEnum;
import com.kuyun.common.db.DynamicDataSource;
import com.kuyun.common.util.CommonUtil;
import com.kuyun.eam.dao.mapper.EamProductLineCompanyMapper;
import com.kuyun.eam.dao.mapper.EamProductLineMapper;
import com.kuyun.eam.dao.model.EamProductLine;
import com.kuyun.eam.dao.model.EamProductLineCompanyExample;
import com.kuyun.eam.dao.model.EamProductLineExample;
import com.kuyun.eam.rpc.api.EamProductLineService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
* EamProductLineService实现
* Created by kuyun on 2018/1/10.
*/
@Service
@Transactional
@BaseService
public class EamProductLineServiceImpl extends BaseServiceImpl<EamProductLineMapper, EamProductLine, EamProductLineExample> implements EamProductLineService {

    private static Logger _log = LoggerFactory.getLogger(EamProductLineServiceImpl.class);

    @Autowired
    EamProductLineMapper eamProductLineMapper;

    @Autowired
    EamProductLineCompanyMapper eamProductLineCompanyMapper;

    @Override
    public int insert(EamProductLine record) {
        record.setProductLineId(CommonUtil.generateRandomToken());
        return super.insert(record);
    }

    @Override
    public int insertSelective(EamProductLine record) {
        record.setProductLineId(CommonUtil.generateRandomToken());
        return super.insertSelective(record);
    }

    @Override
    public EamProductLine selectByPrimaryKey(String id) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
            Method selectByPrimaryKey = eamProductLineMapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
            Object result = selectByPrimaryKey.invoke(eamProductLineMapper, id);
            return (EamProductLine) result;
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
            Method deleteByPrimaryKey = eamProductLineMapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object result = deleteByPrimaryKey.invoke(eamProductLineMapper, id);

            deleteEamProductLineCompany(id);
            return Integer.parseInt(String.valueOf(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
        return 0;
    }

    private void deleteEamProductLineCompany(String productLineId){
        EamProductLineCompanyExample example = new EamProductLineCompanyExample();
        example.createCriteria().andProductLineIdEqualTo(productLineId);

        try {
            Method deleteByExample = eamProductLineCompanyMapper.getClass().getDeclaredMethod("deleteByExample", example.getClass());
            deleteByExample.invoke(eamProductLineCompanyMapper, example);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int deleteByPrimaryKeys(String ids) {
        try {
            if (StringUtils.isBlank(ids)) {
                return 0;
            }
            String[] idArray = ids.split("-");
            int count = 0;
            for (String id : idArray) {
                if (StringUtils.isBlank(id)) {
                    continue;
                }
                count += deleteByPrimaryKey(id);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}