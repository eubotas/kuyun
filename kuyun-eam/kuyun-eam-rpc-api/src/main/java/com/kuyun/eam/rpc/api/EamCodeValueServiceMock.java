package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamCodeValueMapper;
import com.kuyun.eam.dao.model.EamCodeValue;
import com.kuyun.eam.dao.model.EamCodeValueExample;

/**
* 降级实现EamCodeValueService接口
* Created by kuyun on 2018/2/23.
*/
public class EamCodeValueServiceMock extends BaseServiceMock<EamCodeValueMapper, EamCodeValue, EamCodeValueExample> implements EamCodeValueService {

}
