package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamLocationMapper;
import com.kuyun.eam.dao.model.EamLocation;
import com.kuyun.eam.dao.model.EamLocationExample;

/**
* 降级实现EamLocationService接口
* Created by kuyun on 2017/4/20.
*/
public class EamLocationServiceMock extends BaseServiceMock<EamLocationMapper, EamLocation, EamLocationExample> implements EamLocationService {

}
