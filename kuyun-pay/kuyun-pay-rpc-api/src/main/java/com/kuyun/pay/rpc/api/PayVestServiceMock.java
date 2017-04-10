package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayVestMapper;
import com.kuyun.pay.dao.model.PayVest;
import com.kuyun.pay.dao.model.PayVestExample;

/**
* 降级实现PayVestService接口
* Created by kuyun on 2017/3/29.
*/
public class PayVestServiceMock extends BaseServiceMock<PayVestMapper, PayVest, PayVestExample> implements PayVestService {

}
