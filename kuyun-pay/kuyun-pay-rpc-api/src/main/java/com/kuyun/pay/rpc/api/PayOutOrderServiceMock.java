package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayOutOrderMapper;
import com.kuyun.pay.dao.model.PayOutOrder;
import com.kuyun.pay.dao.model.PayOutOrderExample;

/**
* 降级实现PayOutOrderService接口
* Created by kuyun on 2017/3/29.
*/
public class PayOutOrderServiceMock extends BaseServiceMock<PayOutOrderMapper, PayOutOrder, PayOutOrderExample> implements PayOutOrderService {

}
