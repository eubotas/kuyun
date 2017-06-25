package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamEquipmentMapper;
import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentExample;

/**
* 降级实现EamEquipmentService接口
* Created by kuyun on 2017/4/8.
*/
public class EamEquipmentServiceMock extends BaseServiceMock<EamEquipmentMapper, EamEquipment, EamEquipmentExample> implements EamEquipmentService {

    @Override
    public EamEquipment selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }
}
