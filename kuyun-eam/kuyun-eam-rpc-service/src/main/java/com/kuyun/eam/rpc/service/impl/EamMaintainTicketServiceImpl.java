package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamMaintainTicketMapper;
import com.kuyun.eam.dao.model.EamMaintainTicket;
import com.kuyun.eam.dao.model.EamMaintainTicketExample;
import com.kuyun.eam.rpc.api.EamMaintainTicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamMaintainTicketService实现
* Created by kuyun on 2018/2/23.
*/
@Service
@Transactional
@BaseService
public class EamMaintainTicketServiceImpl extends BaseServiceImpl<EamMaintainTicketMapper, EamMaintainTicket, EamMaintainTicketExample> implements EamMaintainTicketService {

    private static Logger _log = LoggerFactory.getLogger(EamMaintainTicketServiceImpl.class);

    @Autowired
    EamMaintainTicketMapper eamMaintainTicketMapper;

}