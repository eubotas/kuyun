package com.kuyun.cms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.cms.dao.mapper.CmsSettingMapper;
import com.kuyun.cms.dao.model.CmsSetting;
import com.kuyun.cms.dao.model.CmsSettingExample;

/**
* 降级实现CmsSettingService接口
* Created by kuyun on 2017/4/5.
*/
public class CmsSettingServiceMock extends BaseServiceMock<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

}
