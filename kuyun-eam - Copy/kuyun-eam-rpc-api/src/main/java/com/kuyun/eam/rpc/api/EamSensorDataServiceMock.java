package com.kuyun.eam.rpc.api;

import com.kuyun.common.base.BaseServiceMock;
import com.kuyun.eam.dao.mapper.EamSensorDataMapper;
import com.kuyun.eam.dao.model.EamSensorData;
import com.kuyun.eam.dao.model.EamSensorDataExample;

/**
* 降级实现EamSensorDataService接口
* Created by kuyun on 2017/6/12.
*/
public class EamSensorDataServiceMock extends BaseServiceMock<EamSensorDataMapper, EamSensorData, EamSensorDataExample> implements EamSensorDataService {

}
