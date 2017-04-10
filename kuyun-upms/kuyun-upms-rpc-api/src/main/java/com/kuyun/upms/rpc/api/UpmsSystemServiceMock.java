package com.kuyun.upms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.upms.dao.mapper.UpmsSystemMapper;
import com.kuyun.upms.dao.model.UpmsSystem;
import com.kuyun.upms.dao.model.UpmsSystemExample;

/**
* 降级实现UpmsSystemService接口
* Created by kuyun on 2017/3/20.
*/
public class UpmsSystemServiceMock extends BaseServiceMock<UpmsSystemMapper, UpmsSystem, UpmsSystemExample> implements UpmsSystemService {

}
