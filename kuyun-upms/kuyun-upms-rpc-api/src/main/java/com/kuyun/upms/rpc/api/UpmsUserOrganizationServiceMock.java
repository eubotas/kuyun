package com.kuyun.upms.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.upms.dao.mapper.UpmsUserOrganizationMapper;
import com.kuyun.upms.dao.model.UpmsUserOrganization;
import com.kuyun.upms.dao.model.UpmsUserOrganizationExample;

import java.util.List;

/**
* 降级实现UpmsUserOrganizationService接口
* Created by kuyun on 2017/3/20.
*/
public class UpmsUserOrganizationServiceMock extends BaseServiceMock<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements UpmsUserOrganizationService {

    @Override
    public void batchInsert(int orgId, List<UpmsUserOrganization> list) {

    }
}
