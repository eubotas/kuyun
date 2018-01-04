package com.kuyun.upms.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.upms.dao.mapper.UpmsUserOrganizationMapper;
import com.kuyun.upms.dao.model.UpmsUserOrganization;
import com.kuyun.upms.dao.model.UpmsUserOrganizationExample;
import com.kuyun.upms.rpc.api.CustUserOrganizationService;
import com.kuyun.upms.rpc.api.UpmsUserOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UpmsUserOrganizationService实现
* Created by kuyun on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class CustUserOrganizationServiceImpl extends BaseServiceImpl<UpmsUserOrganizationMapper, UpmsUserOrganization, UpmsUserOrganizationExample> implements CustUserOrganizationService {

    private static Logger _log = LoggerFactory.getLogger(CustUserOrganizationServiceImpl.class);

    @Autowired
    UpmsUserOrganizationMapper upmsUserOrganizationMapper;

    public void batchInsert(int orgId, List<UpmsUserOrganization> list){
        UpmsUserOrganizationExample example= new UpmsUserOrganizationExample();
        UpmsUserOrganizationExample.Criteria  criteria =example.createCriteria();
        criteria.andOrganizationIdEqualTo(orgId);
        deleteByExample(example);
        batchInsert(list);
    }

}