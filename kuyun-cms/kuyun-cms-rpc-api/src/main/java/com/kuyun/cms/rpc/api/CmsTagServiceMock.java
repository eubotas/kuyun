package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsTagMapper;
import com.kuyun.cms.dao.model.CmsTag;
import com.kuyun.cms.dao.model.CmsTagExample;

/**
* 降级实现CmsTagService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsTagServiceMock extends BaseServiceMock<CmsTagMapper, CmsTag, CmsTagExample> implements CmsTagService {

}
