package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseService;
import com.kuyun.common.db.DataSourceEnum;
import com.kuyun.common.db.DynamicDataSource;
import com.kuyun.eam.dao.model.EamTicketAssessment;
import com.kuyun.eam.dao.model.EamTicketAssessmentExample;

import java.lang.reflect.Method;

/**
* EamTicketAssessmentService接口
* Created by kuyun on 2017/12/26.
*/
public interface EamTicketAssessmentService extends BaseService<EamTicketAssessment, EamTicketAssessmentExample> {

    public EamTicketAssessment insertSelective2(EamTicketAssessment record);
}