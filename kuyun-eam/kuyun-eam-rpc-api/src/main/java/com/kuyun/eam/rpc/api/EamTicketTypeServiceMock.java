package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamTicketTypeMapper;
import com.kuyun.eam.dao.model.EamTicketType;
import com.kuyun.eam.dao.model.EamTicketTypeExample;

/**
* 降级实现EamTicketTypeService接口
* Created by kuyun on 2017/7/19.
*/
public class EamTicketTypeServiceMock extends BaseServiceMock<EamTicketTypeMapper, EamTicketType, EamTicketTypeExample> implements EamTicketTypeService {

}
