package com.kuyun.upms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.upms.dao.mapper.UpmsLogMapper;
import com.kuyun.upms.dao.model.UpmsLog;
import com.kuyun.upms.dao.model.UpmsLogExample;

/**
* 降级实现UpmsLogService接口
* Created by kuyun on 2017/3/20.
*/
public class UpmsLogServiceMock extends BaseServiceMock<UpmsLogMapper, UpmsLog, UpmsLogExample> implements UpmsLogService {

}
