package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsTopicMapper;
import com.kuyun.cms.dao.model.CmsTopic;
import com.kuyun.cms.dao.model.CmsTopicExample;

/**
* 降级实现CmsTopicService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsTopicServiceMock extends BaseServiceMock<CmsTopicMapper, CmsTopic, CmsTopicExample> implements CmsTopicService {

}
