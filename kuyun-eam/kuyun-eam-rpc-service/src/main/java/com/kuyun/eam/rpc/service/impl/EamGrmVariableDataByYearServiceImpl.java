package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableDataByYearMapper;
import com.kuyun.eam.dao.model.EamGrmVariableDataByYear;
import com.kuyun.eam.dao.model.EamGrmVariableDataByYearExample;
import com.kuyun.eam.rpc.api.EamGrmVariableDataByYearService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableDataByYearService实现
* Created by kuyun on 2018/3/28.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableDataByYearServiceImpl extends BaseServiceImpl<EamGrmVariableDataByYearMapper, EamGrmVariableDataByYear, EamGrmVariableDataByYearExample> implements EamGrmVariableDataByYearService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataByYearServiceImpl.class);

    @Autowired
    EamGrmVariableDataByYearMapper eamGrmVariableDataByYearMapper;

}