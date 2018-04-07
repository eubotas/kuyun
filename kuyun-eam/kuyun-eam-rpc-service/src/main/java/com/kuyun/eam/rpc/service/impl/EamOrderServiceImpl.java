package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamOrderMapper;
import com.kuyun.eam.dao.model.EamOrder;
import com.kuyun.eam.dao.model.EamOrderExample;
import com.kuyun.eam.rpc.api.EamOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamOrderService实现
* Created by kuyun on 2018/4/2.
*/
@Service
@Transactional
@BaseService
public class EamOrderServiceImpl extends BaseServiceImpl<EamOrderMapper, EamOrder, EamOrderExample> implements EamOrderService {

    private static Logger _log = LoggerFactory.getLogger(EamOrderServiceImpl.class);

    @Autowired
    EamOrderMapper eamOrderMapper;

}