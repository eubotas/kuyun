package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsCommentMapper;
import com.kuyun.cms.dao.model.CmsComment;
import com.kuyun.cms.dao.model.CmsCommentExample;

/**
* 降级实现CmsCommentService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsCommentServiceMock extends BaseServiceMock<CmsCommentMapper, CmsComment, CmsCommentExample> implements CmsCommentService {

}
