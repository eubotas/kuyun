package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamAlarmMapper;
import com.kuyun.eam.dao.model.EamAlarm;
import com.kuyun.eam.dao.model.EamAlarmExample;

/**
* 降级实现EamAlarmService接口
* Created by kuyun on 2017/6/30.
*/
public class EamAlarmServiceMock extends BaseServiceMock<EamAlarmMapper, EamAlarm, EamAlarmExample> implements EamAlarmService {

}
