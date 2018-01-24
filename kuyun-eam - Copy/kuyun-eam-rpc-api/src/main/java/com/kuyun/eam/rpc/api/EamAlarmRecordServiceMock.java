package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamAlarmRecordMapper;
import com.kuyun.eam.dao.model.EamAlarmRecord;
import com.kuyun.eam.dao.model.EamAlarmRecordExample;

/**
* 降级实现EamAlarmRecordService接口
* Created by kuyun on 2017/9/5.
*/
public class EamAlarmRecordServiceMock extends BaseServiceMock<EamAlarmRecordMapper, EamAlarmRecord, EamAlarmRecordExample> implements EamAlarmRecordService {

}
