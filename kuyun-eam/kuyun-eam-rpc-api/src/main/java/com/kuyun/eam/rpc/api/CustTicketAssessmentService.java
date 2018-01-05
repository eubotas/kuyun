package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.eam.dao.model.EamTicketAssessment;
import com.kuyun.eam.dao.model.EamTicketAssessmentExample;

/**
* EamTicketAssessmentService接口
* Created by kuyun on 2017/12/26.
*/
public interface CustTicketAssessmentService extends BaseService<EamTicketAssessment, EamTicketAssessmentExample> {

    public void createTicketAssessment(EamTicketAssessment record, int[] ticketTag);
    public int updateTicketAssessment(EamTicketAssessment record, int[] ticketTags);
}