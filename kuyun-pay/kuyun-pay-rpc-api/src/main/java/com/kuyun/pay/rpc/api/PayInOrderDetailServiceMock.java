package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayInOrderDetailMapper;
import com.kuyun.pay.dao.model.PayInOrderDetail;
import com.kuyun.pay.dao.model.PayInOrderDetailExample;

/**
* 降级实现PayInOrderDetailService接口
* Created by kuyun on 2017/3/29.
*/
public class PayInOrderDetailServiceMock extends BaseServiceMock<PayInOrderDetailMapper, PayInOrderDetail, PayInOrderDetailExample> implements PayInOrderDetailService {

}
