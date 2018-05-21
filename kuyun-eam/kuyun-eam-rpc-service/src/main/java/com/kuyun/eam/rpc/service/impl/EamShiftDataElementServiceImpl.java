package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamShiftDataElementMapper;
import com.kuyun.eam.dao.model.EamShiftDataElement;
import com.kuyun.eam.dao.model.EamShiftDataElementExample;
import com.kuyun.eam.rpc.api.EamShiftDataElementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamShiftDataElementService实现
* Created by kuyun on 2018/5/21.
*/
@Service
@Transactional
@BaseService
public class EamShiftDataElementServiceImpl extends BaseServiceImpl<EamShiftDataElementMapper, EamShiftDataElement, EamShiftDataElementExample> implements EamShiftDataElementService {

    private static Logger _log = LoggerFactory.getLogger(EamShiftDataElementServiceImpl.class);

    @Autowired
    EamShiftDataElementMapper eamShiftDataElementMapper;

}