package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlertMessageMapper;
import com.kuyun.eam.dao.model.EamAlertMessage;
import com.kuyun.eam.dao.model.EamAlertMessageExample;
import com.kuyun.eam.rpc.api.EamAlertMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlertMessageService实现
* Created by kuyun on 2018/2/23.
*/
@Service
@Transactional
@BaseService
public class EamAlertMessageServiceImpl extends BaseServiceImpl<EamAlertMessageMapper, EamAlertMessage, EamAlertMessageExample> implements EamAlertMessageService {

    private static Logger _log = LoggerFactory.getLogger(EamAlertMessageServiceImpl.class);

    @Autowired
    EamAlertMessageMapper eamAlertMessageMapper;

}