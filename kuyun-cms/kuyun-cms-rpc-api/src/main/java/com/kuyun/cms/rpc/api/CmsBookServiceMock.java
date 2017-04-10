package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsBookMapper;
import com.kuyun.cms.dao.model.CmsBook;
import com.kuyun.cms.dao.model.CmsBookExample;

/**
* 降级实现CmsBookService接口
* Created by kuyun on 2017/4/8.
*/
public class CmsBookServiceMock extends BaseServiceMock<CmsBookMapper, CmsBook, CmsBookExample> implements CmsBookService {

}
