package com.kuyun.fileuploader.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.fileuploader.dao.mapper.FdOssFilesMapper;
import com.kuyun.fileuploader.dao.model.FdOssFiles;
import com.kuyun.fileuploader.dao.model.FdOssFilesExample;
import com.kuyun.fileuploader.rpc.api.FdOssFilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* FdOssFilesService实现
* Created by kuyun on 2017/8/9.
*/
@Service
@Transactional
@BaseService
public class FdOssFilesServiceImpl extends BaseServiceImpl<FdOssFilesMapper, FdOssFiles, FdOssFilesExample> implements FdOssFilesService {

    private static Logger _log = LoggerFactory.getLogger(FdOssFilesServiceImpl.class);

    @Autowired
    FdOssFilesMapper fdOssFilesMapper;

}