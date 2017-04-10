package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayTypeMapper;
import com.kuyun.pay.dao.model.PayType;
import com.kuyun.pay.dao.model.PayTypeExample;

/**
* 降级实现PayTypeService接口
* Created by kuyun on 2017/3/29.
*/
public class PayTypeServiceMock extends BaseServiceMock<PayTypeMapper, PayType, PayTypeExample> implements PayTypeService {

}
