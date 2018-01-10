package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamTicketAppointedRecordMapper;
import com.kuyun.eam.dao.model.EamTicketAppointedRecord;
import com.kuyun.eam.dao.model.EamTicketAppointedRecordExample;
import com.kuyun.eam.rpc.api.EamTicketAppointedRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamTicketAppointedRecordService实现
* Created by kuyun on 2017/12/26.
*/
@Service
@Transactional
@BaseService
public class EamTicketAppointedRecordServiceImpl extends BaseServiceImpl<EamTicketAppointedRecordMapper, EamTicketAppointedRecord, EamTicketAppointedRecordExample> implements EamTicketAppointedRecordService {

    private static Logger _log = LoggerFactory.getLogger(EamTicketAppointedRecordServiceImpl.class);

    @Autowired
    EamTicketAppointedRecordMapper eamTicketAppointedRecordMapper;

}