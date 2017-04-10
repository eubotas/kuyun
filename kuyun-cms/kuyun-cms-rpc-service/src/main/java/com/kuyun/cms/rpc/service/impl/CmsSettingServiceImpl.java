package com.kuyun.cms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.cms.dao.mapper.CmsSettingMapper;
import com.kuyun.cms.dao.model.CmsSetting;
import com.kuyun.cms.dao.model.CmsSettingExample;
import com.kuyun.cms.rpc.api.CmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsSettingService实现
* Created by kuyun on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsSettingServiceImpl extends BaseServiceImpl<CmsSettingMapper, CmsSetting, CmsSettingExample> implements CmsSettingService {

    private static Logger _log = LoggerFactory.getLogger(CmsSettingServiceImpl.class);

    @Autowired
    CmsSettingMapper cmsSettingMapper;

}