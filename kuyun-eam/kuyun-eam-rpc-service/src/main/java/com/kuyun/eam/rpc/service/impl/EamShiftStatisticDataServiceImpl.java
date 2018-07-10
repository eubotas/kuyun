package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamShiftStatisticDataMapper;
import com.kuyun.eam.dao.model.EamShiftStatisticData;
import com.kuyun.eam.dao.model.EamShiftStatisticDataExample;
import com.kuyun.eam.rpc.api.EamShiftStatisticDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamShiftStatisticDataService实现
* Created by kuyun on 2018/7/6.
*/
@Service
@Transactional
@BaseService
public class EamShiftStatisticDataServiceImpl extends BaseServiceImpl<EamShiftStatisticDataMapper, EamShiftStatisticData, EamShiftStatisticDataExample> implements EamShiftStatisticDataService {

    private static Logger _log = LoggerFactory.getLogger(EamShiftStatisticDataServiceImpl.class);

    @Autowired
    EamShiftStatisticDataMapper eamShiftStatisticDataMapper;

}