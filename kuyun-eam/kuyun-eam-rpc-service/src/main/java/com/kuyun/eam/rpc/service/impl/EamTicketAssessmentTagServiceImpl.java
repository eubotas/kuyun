package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamTicketAssessmentTagMapper;
import com.kuyun.eam.dao.model.EamTicketAssessmentTag;
import com.kuyun.eam.dao.model.EamTicketAssessmentTagExample;
import com.kuyun.eam.rpc.api.EamTicketAssessmentTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamTicketAssessmentTagService实现
* Created by kuyun on 2018/1/10.
*/
@Service
@Transactional
@BaseService
public class EamTicketAssessmentTagServiceImpl extends BaseServiceImpl<EamTicketAssessmentTagMapper, EamTicketAssessmentTag, EamTicketAssessmentTagExample> implements EamTicketAssessmentTagService {

    private static Logger _log = LoggerFactory.getLogger(EamTicketAssessmentTagServiceImpl.class);

    @Autowired
    EamTicketAssessmentTagMapper eamTicketAssessmentTagMapper;

}