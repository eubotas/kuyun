package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamTicketTypeMapper;
import com.kuyun.eam.dao.model.EamTicketType;
import com.kuyun.eam.dao.model.EamTicketTypeExample;
import com.kuyun.eam.rpc.api.EamTicketTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamTicketTypeService实现
* Created by kuyun on 2017/7/19.
*/
@Service
@Transactional
@BaseService
public class EamTicketTypeServiceImpl extends BaseServiceImpl<EamTicketTypeMapper, EamTicketType, EamTicketTypeExample> implements EamTicketTypeService {

    private static Logger _log = LoggerFactory.getLogger(EamTicketTypeServiceImpl.class);

    @Autowired
    EamTicketTypeMapper eamTicketTypeMapper;

}