package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamTicketTagMapper;
import com.kuyun.eam.dao.model.EamTicketTag;
import com.kuyun.eam.dao.model.EamTicketTagExample;
import com.kuyun.eam.rpc.api.EamTicketTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamTicketTagService实现
* Created by kuyun on 2017/12/26.
*/
@Service
@Transactional
@BaseService
public class EamTicketTagServiceImpl extends BaseServiceImpl<EamTicketTagMapper, EamTicketTag, EamTicketTagExample> implements EamTicketTagService {

    private static Logger _log = LoggerFactory.getLogger(EamTicketTagServiceImpl.class);

    @Autowired
    EamTicketTagMapper eamTicketTagMapper;

}