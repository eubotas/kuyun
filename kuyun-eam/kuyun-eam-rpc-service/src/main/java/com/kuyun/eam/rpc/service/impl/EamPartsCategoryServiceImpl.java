package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.eam.dao.mapper.EamPartsCategoryMapper;
import com.kuyun.eam.dao.model.EamPartsCategory;
import com.kuyun.eam.dao.model.EamPartsCategoryExample;
import com.kuyun.eam.rpc.api.EamPartsCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* EamPartsCategoryService实现
* Created by kuyun on 2017/4/20.
*/
@Service
@Transactional
@BaseService
public class EamPartsCategoryServiceImpl extends BaseServiceImpl<EamPartsCategoryMapper, EamPartsCategory, EamPartsCategoryExample> implements EamPartsCategoryService {

    private static Logger _log = LoggerFactory.getLogger(EamPartsCategoryServiceImpl.class);

    @Autowired
    EamPartsCategoryMapper eamPartsCategoryMapper;

}