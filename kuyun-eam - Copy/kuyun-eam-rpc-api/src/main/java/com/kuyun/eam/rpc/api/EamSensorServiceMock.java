package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamSensorMapper;
import com.kuyun.eam.dao.model.EamSensor;
import com.kuyun.eam.dao.model.EamSensorExample;

/**
* 降级实现EamSensorService接口
* Created by kuyun on 2017/6/12.
*/
public class EamSensorServiceMock extends BaseServiceMock<EamSensorMapper, EamSensor, EamSensorExample> implements EamSensorService {

}
