package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamAlarmModelMapper;
import com.kuyun.eam.dao.model.EamAlarmModel;
import com.kuyun.eam.dao.model.EamAlarmModelExample;
import com.kuyun.eam.rpc.api.EamAlarmModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamAlarmModelService实现
* Created by kuyun on 2018/1/23.
*/
@Service
@Transactional
@BaseService
public class EamAlarmModelServiceImpl extends BaseServiceImpl<EamAlarmModelMapper, EamAlarmModel, EamAlarmModelExample> implements EamAlarmModelService {

    private static Logger _log = LoggerFactory.getLogger(EamAlarmModelServiceImpl.class);

    @Autowired
    EamAlarmModelMapper eamAlarmModelMapper;

}