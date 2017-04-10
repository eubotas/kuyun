package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsCategoryMapper;
import com.kuyun.cms.dao.model.CmsCategory;
import com.kuyun.cms.dao.model.CmsCategoryExample;

/**
* 降级实现CmsCategoryService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsCategoryServiceMock extends BaseServiceMock<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

}
