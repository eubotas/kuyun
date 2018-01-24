package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.eam.dao.model.EamDtu;
import com.kuyun.eam.dao.model.EamDtuExample;
import com.kuyun.eam.dao.model.EamEquipment;

/**
* EamDtuService接口
* Created by kuyun on 2017/11/4.
*/
public interface EamDtuService extends BaseService<EamDtu, EamDtuExample> {
    EamDtu selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);
}