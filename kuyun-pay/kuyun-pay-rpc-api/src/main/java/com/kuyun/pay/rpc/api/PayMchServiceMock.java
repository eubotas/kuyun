package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayMchMapper;
import com.kuyun.pay.dao.model.PayMch;
import com.kuyun.pay.dao.model.PayMchExample;

/**
* 降级实现PayMchService接口
* Created by kuyun on 2017/3/29.
*/
public class PayMchServiceMock extends BaseServiceMock<PayMchMapper, PayMch, PayMchExample> implements PayMchService {

}
