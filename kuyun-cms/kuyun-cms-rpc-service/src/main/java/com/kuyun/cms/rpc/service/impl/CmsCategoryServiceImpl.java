package com.kuyun.cms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.cms.dao.mapper.CmsCategoryMapper;
import com.kuyun.cms.dao.model.CmsCategory;
import com.kuyun.cms.dao.model.CmsCategoryExample;
import com.kuyun.cms.rpc.api.CmsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* CmsCategoryService实现
* Created by kuyun on 2017/4/5.
*/
@Service
@Transactional
@BaseService
public class CmsCategoryServiceImpl extends BaseServiceImpl<CmsCategoryMapper, CmsCategory, CmsCategoryExample> implements CmsCategoryService {

    private static Logger _log = LoggerFactory.getLogger(CmsCategoryServiceImpl.class);

    @Autowired
    CmsCategoryMapper cmsCategoryMapper;

}