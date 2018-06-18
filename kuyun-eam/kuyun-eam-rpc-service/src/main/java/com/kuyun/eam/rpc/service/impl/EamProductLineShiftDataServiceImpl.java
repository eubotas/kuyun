package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamProductLineShiftDataMapper;
import com.kuyun.eam.dao.model.EamProductLineShiftData;
import com.kuyun.eam.dao.model.EamProductLineShiftDataExample;
import com.kuyun.eam.rpc.api.EamProductLineShiftDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamProductLineShiftDataService实现
* Created by kuyun on 2018/6/10.
*/
@Service
@Transactional
@BaseService
public class EamProductLineShiftDataServiceImpl extends BaseServiceImpl<EamProductLineShiftDataMapper, EamProductLineShiftData, EamProductLineShiftDataExample> implements EamProductLineShiftDataService {

    private static Logger _log = LoggerFactory.getLogger(EamProductLineShiftDataServiceImpl.class);

    @Autowired
    EamProductLineShiftDataMapper eamProductLineShiftDataMapper;

}