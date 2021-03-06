package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamTicketAssessmentMapper;
import com.kuyun.eam.dao.model.EamTicketAssessment;
import com.kuyun.eam.dao.model.EamTicketAssessmentExample;

/**
* 降级实现EamTicketAssessmentService接口
* Created by kuyun on 2017/12/26.
*/
public class EamTicketAssessmentServiceMock extends BaseServiceMock<EamTicketAssessmentMapper, EamTicketAssessment, EamTicketAssessmentExample> implements EamTicketAssessmentService {


    @Override
    public void createTicketAssessment(EamTicketAssessment record, int[] ticketTag) {

    }

    @Override
    public int updateTicketAssessment(EamTicketAssessment record, int[] ticketTags) {
        return 0;
    }
}
