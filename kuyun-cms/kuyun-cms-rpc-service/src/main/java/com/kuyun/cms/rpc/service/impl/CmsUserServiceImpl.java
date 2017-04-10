package com.kuyun.cms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.cms.dao.mapper.CmsUserMapper;
import com.kuyun.cms.dao.model.CmsUser;
import com.kuyun.cms.dao.model.CmsUserExample;
import com.kuyun.cms.rpc.api.CmsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsUserService实现
* Created by kuyun on 2017/4/8.
*/
@Service
@Transactional
@BaseService
public class CmsUserServiceImpl extends BaseServiceImpl<CmsUserMapper, CmsUser, CmsUserExample> implements CmsUserService {

    private static Logger _log = LoggerFactory.getLogger(CmsUserServiceImpl.class);

    @Autowired
    CmsUserMapper cmsUserMapper;

}