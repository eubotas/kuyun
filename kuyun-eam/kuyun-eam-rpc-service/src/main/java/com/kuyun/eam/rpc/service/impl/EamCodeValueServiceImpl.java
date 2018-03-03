package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamCodeValueMapper;
import com.kuyun.eam.dao.model.EamCodeValue;
import com.kuyun.eam.dao.model.EamCodeValueExample;
import com.kuyun.eam.rpc.api.EamCodeValueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamCodeValueService实现
* Created by kuyun on 2018/1/24.
*/
@Service
@Transactional
@BaseService
public class EamCodeValueServiceImpl extends BaseServiceImpl<EamCodeValueMapper, EamCodeValue, EamCodeValueExample> implements EamCodeValueService {

    private static Logger _log = LoggerFactory.getLogger(EamCodeValueServiceImpl.class);

    @Autowired
    EamCodeValueMapper eamCodeValueMapper;

}