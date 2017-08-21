package com.kuyun.fileuploader.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.fileuploader.dao.mapper.FdFilesMapper;
import com.kuyun.fileuploader.dao.model.FdFiles;
import com.kuyun.fileuploader.dao.model.FdFilesExample;

/**
* 降级实现FdFilesService接口
* Created by kuyun on 2017/8/9.
*/
public class FdFilesServiceMock extends BaseServiceMock<FdFilesMapper, FdFiles, FdFilesExample> implements FdFilesService {

}
