package com.kuyun.pay.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.pay.dao.mapper.PayPayMapper;
import com.kuyun.pay.dao.model.PayPay;
import com.kuyun.pay.dao.model.PayPayExample;
import com.kuyun.pay.rpc.api.PayPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PayPayService实现
* Created by kuyun on 2017/3/29.
*/
@Service
@Transactional
@BaseService
public class PayPayServiceImpl extends BaseServiceImpl<PayPayMapper, PayPay, PayPayExample> implements PayPayService {

    private static Logger _log = LoggerFactory.getLogger(PayPayServiceImpl.class);

    @Autowired
    PayPayMapper payPayMapper;

}