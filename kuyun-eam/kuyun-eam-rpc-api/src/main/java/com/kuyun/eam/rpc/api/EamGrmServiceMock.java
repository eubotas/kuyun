package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamGrmMapper;
import com.kuyun.eam.dao.model.EamGrm;
import com.kuyun.eam.dao.model.EamGrmExample;

/**
* 降级实现EamGrmService接口
* Created by kuyun on 2018/4/17.
*/
public class EamGrmServiceMock extends BaseServiceMock<EamGrmMapper, EamGrm, EamGrmExample> implements EamGrmService {

    @Override
    public EamGrm selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }
}
