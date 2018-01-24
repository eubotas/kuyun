package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamDataElementMapper;
import com.kuyun.eam.dao.model.EamDataElement;
import com.kuyun.eam.dao.model.EamDataElementExample;

/**
* 降级实现EamDataElementService接口
* Created by kuyun on 2018/1/23.
*/
public class EamDataElementServiceMock extends BaseServiceMock<EamDataElementMapper, EamDataElement, EamDataElementExample> implements EamDataElementService {

}
