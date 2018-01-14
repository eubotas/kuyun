package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamProductLineMapper;
import com.kuyun.eam.dao.model.EamProductLine;
import com.kuyun.eam.dao.model.EamProductLineExample;

/**
* 降级实现EamProductLineService接口
* Created by kuyun on 2018/1/10.
*/
public class EamProductLineServiceMock extends BaseServiceMock<EamProductLineMapper, EamProductLine, EamProductLineExample> implements EamProductLineService {

    @Override
    public EamProductLine selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }
}
