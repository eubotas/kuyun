package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.eam.dao.model.EamDtu;
import com.kuyun.eam.dao.model.EamGrm;
import com.kuyun.eam.dao.model.EamGrmExample;

/**
* EamGrmService接口
* Created by kuyun on 2018/4/17.
*/
public interface EamGrmService extends BaseService<EamGrm, EamGrmExample> {
    EamGrm selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);
}