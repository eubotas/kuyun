package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamPartsMapper;
import com.kuyun.eam.dao.model.EamParts;
import com.kuyun.eam.dao.model.EamPartsExample;

/**
* 降级实现EamPartsService接口
* Created by kuyun on 2017/4/20.
*/
public class EamPartsServiceMock extends BaseServiceMock<EamPartsMapper, EamParts, EamPartsExample> implements EamPartsService {

}
