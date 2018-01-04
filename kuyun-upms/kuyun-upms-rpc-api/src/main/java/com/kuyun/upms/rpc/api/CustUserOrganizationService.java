package com.kuyun.upms.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.upms.dao.model.UpmsUserOrganization;
import com.kuyun.upms.dao.model.UpmsUserOrganizationExample;

import java.util.List;

/**
* UpmsUserOrganizationService接口
* Created by kuyun on 2017/3/20.
*/
public interface CustUserOrganizationService extends BaseService<UpmsUserOrganization, UpmsUserOrganizationExample> {

    public void batchInsert(int orgId, List<UpmsUserOrganization> list);
}