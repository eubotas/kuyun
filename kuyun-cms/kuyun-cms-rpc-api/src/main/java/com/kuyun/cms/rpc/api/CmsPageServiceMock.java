package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsPageMapper;
import com.kuyun.cms.dao.model.CmsPage;
import com.kuyun.cms.dao.model.CmsPageExample;

/**
* 降级实现CmsPageService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsPageServiceMock extends BaseServiceMock<CmsPageMapper, CmsPage, CmsPageExample> implements CmsPageService {

}
