package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamTicketRecordMapper;
import com.kuyun.eam.dao.model.EamTicketRecord;
import com.kuyun.eam.dao.model.EamTicketRecordExample;
import com.kuyun.eam.rpc.api.EamTicketRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamTicketRecordService实现
* Created by kuyun on 2017/7/19.
*/
@Service
@Transactional
@BaseService
public class EamTicketRecordServiceImpl extends BaseServiceImpl<EamTicketRecordMapper, EamTicketRecord, EamTicketRecordExample> implements EamTicketRecordService {

    private static Logger _log = LoggerFactory.getLogger(EamTicketRecordServiceImpl.class);

    @Autowired
    EamTicketRecordMapper eamTicketRecordMapper;

}