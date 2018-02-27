package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamAlertMessageMapper;
import com.kuyun.eam.dao.model.EamAlertMessage;
import com.kuyun.eam.dao.model.EamAlertMessageExample;

/**
* 降级实现EamAlertMessageService接口
* Created by kuyun on 2018/2/23.
*/
public class EamAlertMessageServiceMock extends BaseServiceMock<EamAlertMessageMapper, EamAlertMessage, EamAlertMessageExample> implements EamAlertMessageService {

}
