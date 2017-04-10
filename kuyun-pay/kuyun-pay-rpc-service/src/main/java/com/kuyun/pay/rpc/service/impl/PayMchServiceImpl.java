package com.kuyun.pay.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.pay.dao.mapper.PayMchMapper;
import com.kuyun.pay.dao.model.PayMch;
import com.kuyun.pay.dao.model.PayMchExample;
import com.kuyun.pay.rpc.api.PayMchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PayMchService实现
* Created by kuyun on 2017/3/29.
*/
@Service
@Transactional
@BaseService
public class PayMchServiceImpl extends BaseServiceImpl<PayMchMapper, PayMch, PayMchExample> implements PayMchService {

    private static Logger _log = LoggerFactory.getLogger(PayMchServiceImpl.class);

    @Autowired
    PayMchMapper payMchMapper;

}