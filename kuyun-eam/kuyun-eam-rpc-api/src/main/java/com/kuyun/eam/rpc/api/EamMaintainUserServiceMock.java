package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamMaintainUserMapper;
import com.kuyun.eam.dao.model.EamMaintainUser;
import com.kuyun.eam.dao.model.EamMaintainUserExample;

/**
* 降级实现EamMaintainUserService接口
* Created by kuyun on 2018/4/23.
*/
public class EamMaintainUserServiceMock extends BaseServiceMock<EamMaintainUserMapper, EamMaintainUser, EamMaintainUserExample> implements EamMaintainUserService {

}
