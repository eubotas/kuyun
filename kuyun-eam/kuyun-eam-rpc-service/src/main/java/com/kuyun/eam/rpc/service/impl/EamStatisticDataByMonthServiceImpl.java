package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamStatisticDataByMonthMapper;
import com.kuyun.eam.dao.model.EamStatisticDataByMonth;
import com.kuyun.eam.dao.model.EamStatisticDataByMonthExample;
import com.kuyun.eam.rpc.api.EamStatisticDataByMonthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamStatisticDataByMonthService实现
* Created by kuyun on 2018/7/6.
*/
@Service
@Transactional
@BaseService
public class EamStatisticDataByMonthServiceImpl extends BaseServiceImpl<EamStatisticDataByMonthMapper, EamStatisticDataByMonth, EamStatisticDataByMonthExample> implements EamStatisticDataByMonthService {

    private static Logger _log = LoggerFactory.getLogger(EamStatisticDataByMonthServiceImpl.class);

    @Autowired
    EamStatisticDataByMonthMapper eamStatisticDataByMonthMapper;

}