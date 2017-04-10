package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsMenuMapper;
import com.kuyun.cms.dao.model.CmsMenu;
import com.kuyun.cms.dao.model.CmsMenuExample;

/**
* 降级实现CmsMenuService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsMenuServiceMock extends BaseServiceMock<CmsMenuMapper, CmsMenu, CmsMenuExample> implements CmsMenuService {

}
