package com.kuyun.marketing.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.marketing.dao.mapper.MktSmsMapper;
import com.kuyun.marketing.dao.model.MktSms;
import com.kuyun.marketing.dao.model.MktSmsExample;

/**
* 降级实现MktSmsService接口
* Created by kuyun on 2017/7/22.
*/
public class MktSmsServiceMock extends BaseServiceMock<MktSmsMapper, MktSms, MktSmsExample> implements MktSmsService {

}
