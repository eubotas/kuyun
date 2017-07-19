package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamTicketMapper;
import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketExample;

/**
* 降级实现EamTicketService接口
* Created by kuyun on 2017/7/19.
*/
public class EamTicketServiceMock extends BaseServiceMock<EamTicketMapper, EamTicket, EamTicketExample> implements EamTicketService {

}
