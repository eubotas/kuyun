package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamGrmVariableDataByDayMapper;
import com.kuyun.eam.dao.model.EamGrmVariableDataByDay;
import com.kuyun.eam.dao.model.EamGrmVariableDataByDayExample;
import com.kuyun.eam.rpc.api.EamGrmVariableDataByDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamGrmVariableDataByDayService实现
* Created by kuyun on 2018/3/28.
*/
@Service
@Transactional
@BaseService
public class EamGrmVariableDataByDayServiceImpl extends BaseServiceImpl<EamGrmVariableDataByDayMapper, EamGrmVariableDataByDay, EamGrmVariableDataByDayExample> implements EamGrmVariableDataByDayService {

    private static Logger _log = LoggerFactory.getLogger(EamGrmVariableDataByDayServiceImpl.class);

    @Autowired
    EamGrmVariableDataByDayMapper eamGrmVariableDataByDayMapper;

}