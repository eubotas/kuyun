package com.kuyun.pay.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.pay.dao.mapper.PayInOrderDetailMapper;
import com.kuyun.pay.dao.model.PayInOrderDetail;
import com.kuyun.pay.dao.model.PayInOrderDetailExample;
import com.kuyun.pay.rpc.api.PayInOrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PayInOrderDetailService实现
* Created by kuyun on 2017/3/29.
*/
@Service
@Transactional
@BaseService
public class PayInOrderDetailServiceImpl extends BaseServiceImpl<PayInOrderDetailMapper, PayInOrderDetail, PayInOrderDetailExample> implements PayInOrderDetailService {

    private static Logger _log = LoggerFactory.getLogger(PayInOrderDetailServiceImpl.class);

    @Autowired
    PayInOrderDetailMapper payInOrderDetailMapper;

}