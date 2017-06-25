package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamProtocolMapper;
import com.kuyun.eam.dao.model.EamProtocol;
import com.kuyun.eam.dao.model.EamProtocolExample;

/**
* 降级实现EamProtocolService接口
* Created by kuyun on 2017/6/12.
*/
public class EamProtocolServiceMock extends BaseServiceMock<EamProtocolMapper, EamProtocol, EamProtocolExample> implements EamProtocolService {

}
