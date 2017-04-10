package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayPayMapper;
import com.kuyun.pay.dao.model.PayPay;
import com.kuyun.pay.dao.model.PayPayExample;

/**
* 降级实现PayPayService接口
* Created by kuyun on 2017/3/29.
*/
public class PayPayServiceMock extends BaseServiceMock<PayPayMapper, PayPay, PayPayExample> implements PayPayService {

}
