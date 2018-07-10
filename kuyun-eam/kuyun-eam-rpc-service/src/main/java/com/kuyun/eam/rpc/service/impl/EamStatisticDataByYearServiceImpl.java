package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamStatisticDataByYearMapper;
import com.kuyun.eam.dao.model.EamStatisticDataByYear;
import com.kuyun.eam.dao.model.EamStatisticDataByYearExample;
import com.kuyun.eam.rpc.api.EamStatisticDataByYearService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamStatisticDataByYearService实现
* Created by kuyun on 2018/7/6.
*/
@Service
@Transactional
@BaseService
public class EamStatisticDataByYearServiceImpl extends BaseServiceImpl<EamStatisticDataByYearMapper, EamStatisticDataByYear, EamStatisticDataByYearExample> implements EamStatisticDataByYearService {

    private static Logger _log = LoggerFactory.getLogger(EamStatisticDataByYearServiceImpl.class);

    @Autowired
    EamStatisticDataByYearMapper eamStatisticDataByYearMapper;

}