package com.kuyun.eam.rpc.api;

import com.kuyun.eam.vo.EamEquipmentModelPropertiesVO;

/**
 * Created by user on 2017-11-02.
 */
public class EamWriteDataServiceMock implements EamWriteDataService {
    @Override
    public boolean sensorWrite(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO) {
        return false;
    }
}
