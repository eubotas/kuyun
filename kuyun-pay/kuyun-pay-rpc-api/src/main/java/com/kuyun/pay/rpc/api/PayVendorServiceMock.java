package com.kuyun.pay.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.pay.dao.mapper.PayVendorMapper;
import com.kuyun.pay.dao.model.PayVendor;
import com.kuyun.pay.dao.model.PayVendorExample;

/**
* 降级实现PayVendorService接口
* Created by kuyun on 2017/3/29.
*/
public class PayVendorServiceMock extends BaseServiceMock<PayVendorMapper, PayVendor, PayVendorExample> implements PayVendorService {

}
