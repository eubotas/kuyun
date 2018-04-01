package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableDataByMonthMapper;
import com.kuyun.eam.dao.model.EamGrmVariableDataByMonth;
import com.kuyun.eam.dao.model.EamGrmVariableDataByMonthExample;
import com.kuyun.eam.rpc.api.EamGrmVariableDataByMonthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableDataByMonthService实现
* Created by kuyun on 2018/3/28.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableDataByMonthServiceImpl extends BaseServiceImpl<EamGrmVariableDataByMonthMapper, EamGrmVariableDataByMonth, EamGrmVariableDataByMonthExample> implements EamGrmVariableDataByMonthService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataByMonthServiceImpl.class);

    @Autowired
    EamGrmVariableDataByMonthMapper eamGrmVariableDataByMonthMapper;

}