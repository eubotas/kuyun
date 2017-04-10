package com.kuyun.pay.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.pay.dao.mapper.PayOutOrderDetailMapper;
import com.kuyun.pay.dao.model.PayOutOrderDetail;
import com.kuyun.pay.dao.model.PayOutOrderDetailExample;
import com.kuyun.pay.rpc.api.PayOutOrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PayOutOrderDetailService实现
* Created by kuyun on 2017/3/29.
*/
@Service
@Transactional
@BaseService
public class PayOutOrderDetailServiceImpl extends BaseServiceImpl<PayOutOrderDetailMapper, PayOutOrderDetail, PayOutOrderDetailExample> implements PayOutOrderDetailService {

    private static Logger _log = LoggerFactory.getLogger(PayOutOrderDetailServiceImpl.class);

    @Autowired
    PayOutOrderDetailMapper payOutOrderDetailMapper;

}