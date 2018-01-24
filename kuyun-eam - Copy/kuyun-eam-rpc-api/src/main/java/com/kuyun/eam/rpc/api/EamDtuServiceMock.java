package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamDtuMapper;
import com.kuyun.eam.dao.model.EamDtu;
import com.kuyun.eam.dao.model.EamDtuExample;

/**
* 降级实现EamDtuService接口
* Created by kuyun on 2017/11/4.
*/
public class EamDtuServiceMock extends BaseServiceMock<EamDtuMapper, EamDtu, EamDtuExample> implements EamDtuService {

    @Override
    public EamDtu selectByPrimaryKey(String id) {
        return null;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }
}
