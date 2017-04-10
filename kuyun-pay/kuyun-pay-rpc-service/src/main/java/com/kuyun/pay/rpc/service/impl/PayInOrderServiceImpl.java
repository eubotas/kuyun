package com.kuyun.pay.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.pay.dao.mapper.PayInOrderMapper;
import com.kuyun.pay.dao.model.PayInOrder;
import com.kuyun.pay.dao.model.PayInOrderExample;
import com.kuyun.pay.rpc.api.PayInOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PayInOrderService实现
* Created by kuyun on 2017/3/29.
*/
@Service
@Transactional
@BaseService
public class PayInOrderServiceImpl extends BaseServiceImpl<PayInOrderMapper, PayInOrder, PayInOrderExample> implements PayInOrderService {

    private static Logger _log = LoggerFactory.getLogger(PayInOrderServiceImpl.class);

    @Autowired
    PayInOrderMapper payInOrderMapper;

}