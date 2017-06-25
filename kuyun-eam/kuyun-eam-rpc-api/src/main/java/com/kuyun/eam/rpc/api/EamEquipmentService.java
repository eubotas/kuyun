package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.eam.dao.model.EamEquipment;
import com.kuyun.eam.dao.model.EamEquipmentExample;

/**
* EamEquipmentService接口
* Created by kuyun on 2017/4/8.
*/
public interface EamEquipmentService extends BaseService<EamEquipment, EamEquipmentExample> {

    EamEquipment selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);
}