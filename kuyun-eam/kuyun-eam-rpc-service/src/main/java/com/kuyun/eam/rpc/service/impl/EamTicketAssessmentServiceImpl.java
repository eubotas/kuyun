package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamTicketAssessmentMapper;
import com.kuyun.eam.dao.model.EamTicketAssessment;
import com.kuyun.eam.dao.model.EamTicketAssessmentExample;
import com.kuyun.eam.rpc.api.EamTicketAssessmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamTicketAssessmentService实现
* Created by kuyun on 2018/1/10.
*/
@Service
@Transactional
@BaseService
public class EamTicketAssessmentServiceImpl extends CustTicketAssessmentServiceImpl implements EamTicketAssessmentService {

    private static Logger _log = LoggerFactory.getLogger(EamTicketAssessmentServiceImpl.class);

    @Autowired
    EamTicketAssessmentMapper eamTicketAssessmentMapper;

}