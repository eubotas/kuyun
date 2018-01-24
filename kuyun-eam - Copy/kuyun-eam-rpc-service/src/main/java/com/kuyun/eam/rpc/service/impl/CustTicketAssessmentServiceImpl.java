package com.kuyun.eam.rpc.service.impl;

import com.kuyun.common.annotation.BaseService;
import com.kuyun.common.base.BaseServiceImpl;
import com.kuyun.common.db.DataSourceEnum;
import com.kuyun.common.db.DynamicDataSource;
import com.kuyun.eam.dao.mapper.EamTicketAssessmentMapper;
import com.kuyun.eam.dao.model.EamTicketAssessment;
import com.kuyun.eam.dao.model.EamTicketAssessmentExample;
import com.kuyun.eam.dao.model.EamTicketAssessmentTag;
import com.kuyun.eam.dao.model.EamTicketAssessmentTagExample;
import com.kuyun.eam.rpc.api.CustTicketAssessmentService;
import com.kuyun.eam.rpc.api.EamTicketAssessmentService;
import com.kuyun.eam.rpc.api.EamTicketAssessmentTagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* EamTicketAssessmentService实现
* Created by kuyun on 2017/12/26.
*/
@Service
@Transactional
@BaseService
public class CustTicketAssessmentServiceImpl extends BaseServiceImpl<EamTicketAssessmentMapper, EamTicketAssessment, EamTicketAssessmentExample> implements CustTicketAssessmentService {

    private static Logger _log = LoggerFactory.getLogger(CustTicketAssessmentServiceImpl.class);

    @Autowired
    private EamTicketAssessmentTagService eamTicketAssessmentTagService;

    @Autowired
    private EamTicketAssessmentService eamTicketAssessmentService;

    @Autowired
    EamTicketAssessmentMapper eamTicketAssessmentMapper;

    @Override
    public void createTicketAssessment(EamTicketAssessment record, int[] ticketTag) {
        try {
            DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
            Method insertSelective = mapper.getClass().getDeclaredMethod("insertSelective", record.getClass());
            Object result = insertSelective.invoke(mapper, record);

            insertTag(record, ticketTag);

        } catch (Exception e) {
            e.printStackTrace();
        }
        DynamicDataSource.clearDataSource();
    }

    @Override
    public int updateTicketAssessment(EamTicketAssessment record, int[] ticketTags) {
        //delete original TAG
        EamTicketAssessmentTagExample eamTicketAssessmentTagExample = new EamTicketAssessmentTagExample();
        EamTicketAssessmentTagExample.Criteria criteria = eamTicketAssessmentTagExample.createCriteria();
        criteria.andAssessmentIdEqualTo(record.getId());
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);
        eamTicketAssessmentTagService.deleteByExample(eamTicketAssessmentTagExample);

        //insert new tag
        insertTag(record, ticketTags);

        //update assessment
        return eamTicketAssessmentService.updateByPrimaryKeySelective(record);
    }

    private void insertTag(EamTicketAssessment record, int[] ticketTags){
        //insert tag
        List<EamTicketAssessmentTag> items=new ArrayList<EamTicketAssessmentTag>();
        EamTicketAssessmentTag tag=null;
        for(int i : ticketTags){
            tag=new EamTicketAssessmentTag();
            tag.setTagId(i);
            tag.setTicketId(record.getTicketId());
            tag.setAssessmentId(record.getId());
            tag.setCompanyId(record.getCompanyId());
            tag.setCreateUserId(record.getCreateUserId());
            tag.setDeleteFlag(false);
            tag.setCreateTime(new Date());
            items.add(tag);
        }
        eamTicketAssessmentTagService.batchInsert(items);
    }
}