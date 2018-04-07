package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamOrderMapper;
import com.kuyun.eam.dao.model.EamOrder;
import com.kuyun.eam.dao.model.EamOrderExample;

/**
* 降级实现EamOrderService接口
* Created by kuyun on 2018/4/2.
*/
public class EamOrderServiceMock extends BaseServiceMock<EamOrderMapper, EamOrder, EamOrderExample> implements EamOrderService {

}
