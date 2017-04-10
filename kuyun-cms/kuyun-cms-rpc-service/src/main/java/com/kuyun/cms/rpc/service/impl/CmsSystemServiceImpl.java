package com.kuyun.cms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.cms.dao.mapper.CmsSystemMapper;
import com.kuyun.cms.dao.model.CmsSystem;
import com.kuyun.cms.dao.model.CmsSystemExample;
import com.kuyun.cms.rpc.api.CmsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsSystemService实现
* Created by kuyun on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsSystemServiceImpl extends BaseServiceImpl<CmsSystemMapper, CmsSystem, CmsSystemExample> implements CmsSystemService {

    private static Logger _log = LoggerFactory.getLogger(CmsSystemServiceImpl.class);

    @Autowired
    CmsSystemMapper cmsSystemMapper;

}