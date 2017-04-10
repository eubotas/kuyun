package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsSystemMapper;
import com.kuyun.cms.dao.model.CmsSystem;
import com.kuyun.cms.dao.model.CmsSystemExample;

/**
* 降级实现CmsSystemService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsSystemServiceMock extends BaseServiceMock<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

}
