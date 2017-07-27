package com.kuyun.marketing.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.marketing.dao.mapper.MktSmsUserMapper;
import com.kuyun.marketing.dao.model.MktSmsUser;
import com.kuyun.marketing.dao.model.MktSmsUserExample;

/**
* 降级实现MktSmsUserService接口
* Created by kuyun on 2017/7/22.
*/
public class MktSmsUserServiceMock extends BaseServiceMock<MktSmsUserMapper, MktSmsUser, MktSmsUserExample> implements MktSmsUserService {

}
