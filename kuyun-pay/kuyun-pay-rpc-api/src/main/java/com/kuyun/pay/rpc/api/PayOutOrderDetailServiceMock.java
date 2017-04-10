package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayOutOrderDetailMapper;
import com.kuyun.pay.dao.model.PayOutOrderDetail;
import com.kuyun.pay.dao.model.PayOutOrderDetailExample;

/**
* 降级实现PayOutOrderDetailService接口
* Created by kuyun on 2017/3/29.
*/
public class PayOutOrderDetailServiceMock extends BaseServiceMock<PayOutOrderDetailMapper, PayOutOrderDetail, PayOutOrderDetailExample> implements PayOutOrderDetailService {

}
