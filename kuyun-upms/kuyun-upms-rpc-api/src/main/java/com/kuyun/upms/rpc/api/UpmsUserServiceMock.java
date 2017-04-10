package com.kuyun.upms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.upms.dao.mapper.UpmsUserMapper;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserExample;

/**
* 降级实现UpmsUserService接口
* Created by kuyun on 2017/3/20.
*/
public class UpmsUserServiceMock extends BaseServiceMock<UpmsUserMapper, UpmsUser, UpmsUserExample> implements UpmsUserService {

}
