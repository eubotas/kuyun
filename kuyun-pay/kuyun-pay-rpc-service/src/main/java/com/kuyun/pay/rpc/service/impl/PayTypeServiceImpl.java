package com.kuyun.pay.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.pay.dao.mapper.PayTypeMapper;
import com.kuyun.pay.dao.model.PayType;
import com.kuyun.pay.dao.model.PayTypeExample;
import com.kuyun.pay.rpc.api.PayTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PayTypeService实现
* Created by kuyun on 2017/3/29.
*/
@Service
@Transactional
@BaseService
public class PayTypeServiceImpl extends BaseServiceImpl<PayTypeMapper, PayType, PayTypeExample> implements PayTypeService {

    private static Logger _log = LoggerFactory.getLogger(PayTypeServiceImpl.class);

    @Autowired
    PayTypeMapper payTypeMapper;

}