package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamShiftDataElementValueMapper;
import com.kuyun.eam.dao.model.EamShiftDataElementValue;
import com.kuyun.eam.dao.model.EamShiftDataElementValueExample;
import com.kuyun.eam.rpc.api.EamShiftDataElementValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamShiftDataElementValueService实现
* Created by kuyun on 2018/5/21.
*/
@Service
@Transactional
@BaseService
public class EamShiftDataElementValueServiceImpl extends BaseServiceImpl<EamShiftDataElementValueMapper, EamShiftDataElementValue, EamShiftDataElementValueExample> implements EamShiftDataElementValueService {

    private static Logger _log = LoggerFactory.getLogger(EamShiftDataElementValueServiceImpl.class);

    @Autowired
    EamShiftDataElementValueMapper eamShiftDataElementValueMapper;

}