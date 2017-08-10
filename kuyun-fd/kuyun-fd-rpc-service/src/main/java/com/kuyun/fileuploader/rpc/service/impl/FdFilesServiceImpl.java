package com.kuyun.fileuploader.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.fileuploader.dao.mapper.FdFilesMapper;
import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.dao.model.FdFilesExample;
import com.kuyun.fileuploader.rpc.api.FdFilesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* FdFilesService实现
* Created by kuyun on 2017/8/9.
*/
@Service
@Transactional
@BaseService
public class FdFilesServiceImpl extends BaseServiceImpl<FdFilesMapper, FdFiles, FdFilesExample> implements FdFilesService {

    private static Logger _log = LoggerFactory.getLogger(FdFilesServiceImpl.class);

    @Autowired
    FdFilesMapper fdFilesMapper;

}