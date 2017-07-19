package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamTicketMapper;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketExample;
import com.kuyun.eam.rpc.api.EamTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamTicketService实现
* Created by kuyun on 2017/7/19.
*/
@Service
@Transactional
@BaseService
public class EamTicketServiceImpl extends BaseServiceImpl<EamTicketMapper, EamTicket, EamTicketExample> implements EamTicketService {

    private static Logger _log = LoggerFactory.getLogger(EamTicketServiceImpl.class);

    @Autowired
    EamTicketMapper eamTicketMapper;

}