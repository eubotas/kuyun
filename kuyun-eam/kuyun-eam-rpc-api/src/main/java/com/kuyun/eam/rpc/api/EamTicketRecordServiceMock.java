package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamTicketRecordMapper;
import com.kuyun.eam.dao.model.EamTicketRecord;
import com.kuyun.eam.dao.model.EamTicketRecordExample;

/**
* 降级实现EamTicketRecordService接口
* Created by kuyun on 2017/7/19.
*/
public class EamTicketRecordServiceMock extends BaseServiceMock<EamTicketRecordMapper, EamTicketRecord, EamTicketRecordExample> implements EamTicketRecordService {

}
