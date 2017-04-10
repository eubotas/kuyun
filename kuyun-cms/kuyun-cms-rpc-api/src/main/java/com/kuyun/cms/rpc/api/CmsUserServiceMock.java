package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsUserMapper;
import com.kuyun.cms.dao.model.CmsUser;
import com.kuyun.cms.dao.model.CmsUserExample;

/**
* 降级实现CmsUserService接口
* Created by kuyun on 2017/4/8.
*/
public class CmsUserServiceMock extends BaseServiceMock<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

}
