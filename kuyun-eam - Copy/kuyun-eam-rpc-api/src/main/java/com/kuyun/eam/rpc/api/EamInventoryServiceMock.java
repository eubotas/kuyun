package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamInventoryMapper;
import com.kuyun.eam.dao.model.EamInventory;
import com.kuyun.eam.dao.model.EamInventoryExample;

/**
* 降级实现EamInventoryService接口
* Created by kuyun on 2017/4/20.
*/
public class EamInventoryServiceMock extends BaseServiceMock<EamInventoryMapper, EamInventory, EamInventoryExample> implements EamInventoryService {

}
