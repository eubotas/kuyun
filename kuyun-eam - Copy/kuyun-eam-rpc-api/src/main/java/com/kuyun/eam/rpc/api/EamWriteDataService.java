package com.kuyun.eam.rpc.api;

import com.kuyun.eam.vo.EamEquipmentModelPropertiesVO;

/**
 * Created by user on 2017-11-02.
 */
public interface EamWriteDataService {
    boolean sensorWrite(EamEquipmentModelPropertiesVO equipmentModelPropertiesVO);
}
