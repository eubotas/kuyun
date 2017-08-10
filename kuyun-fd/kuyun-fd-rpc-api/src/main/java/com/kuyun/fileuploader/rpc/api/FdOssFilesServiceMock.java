package com.kuyun.fileuploader.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.fileuploader.dao.mapper.FdOssFilesMapper;
import com.kuyun.fileuploader.dao.model.FdOssFiles;
import com.kuyun.fileuploader.dao.model.FdOssFilesExample;

/**
* 降级实现FdOssFilesService接口
* Created by kuyun on 2017/8/9.
*/
public class FdOssFilesServiceMock extends BaseServiceMock<FdOssFilesMapper, FdOssFiles, FdOssFilesExample> implements FdOssFilesService {

}
