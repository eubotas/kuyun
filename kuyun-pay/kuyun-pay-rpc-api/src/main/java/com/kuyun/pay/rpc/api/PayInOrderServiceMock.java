package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayInOrderMapper;
import com.kuyun.pay.dao.model.PayInOrder;
import com.kuyun.pay.dao.model.PayInOrderExample;

/**
* 降级实现PayInOrderService接口
* Created by kuyun on 2017/3/29.
*/
public class PayInOrderServiceMock extends BaseServiceMock<PayInOrderMapper, PayInOrder, PayInOrderExample> implements PayInOrderService {

}
