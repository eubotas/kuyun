package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsArticleMapper;
import com.kuyun.cms.dao.model.CmsArticle;
import com.kuyun.cms.dao.model.CmsArticleExample;

/**
* 降级实现CmsArticleService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsArticleServiceMock extends BaseServiceMock<CmsArticleMapper, CmsArticle, CmsArticleExample> implements CmsArticleService {

}
