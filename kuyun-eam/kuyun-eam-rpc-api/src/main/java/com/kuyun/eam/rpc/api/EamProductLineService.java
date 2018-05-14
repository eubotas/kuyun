package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.eam.dao.model.EamProductLine;
import com.kuyun.eam.dao.model.EamProductLineExample;

/**
* EamProductLineService接口
* Created by kuyun on 2018/1/10.
*/
public interface EamProductLineService extends BaseService<EamProductLine, EamProductLineExample> {

    EamProductLine selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);
}