package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamStatisticDataByDayMapper;
import com.kuyun.eam.dao.model.EamStatisticDataByDay;
import com.kuyun.eam.dao.model.EamStatisticDataByDayExample;
import com.kuyun.eam.rpc.api.EamStatisticDataByDayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamStatisticDataByDayService实现
* Created by kuyun on 2018/7/6.
*/
@Service
@Transactional
@BaseService
public class EamStatisticDataByDayServiceImpl extends BaseServiceImpl<EamStatisticDataByDayMapper, EamStatisticDataByDay, EamStatisticDataByDayExample> implements EamStatisticDataByDayService {

    private static Logger _log = LoggerFactory.getLogger(EamStatisticDataByDayServiceImpl.class);

    @Autowired
    EamStatisticDataByDayMapper eamStatisticDataByDayMapper;

}