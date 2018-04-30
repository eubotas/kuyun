package com.kuyun.eam.admin.util;

import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-10-17.
 */
public class EamUtil {

    @Autowired
    private BaseEntityUtil baseEntityUtil;

    @Autowired
    private EamApiService eamApiService;


    public List<EamEquipmentVO> getCurrentCompanyEquipments(){
        EamEquipmentVO equipmentVO = new EamEquipmentVO();
        equipmentVO.setDeleteFlag(Boolean.FALSE);
        equipmentVO.setOrderByClause("eam_equipment.equipment_model_id desc");
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            equipmentVO.setCompanyId(company.getCompanyId());
        }
        return eamApiService.selectEquipments(equipmentVO);
    }

    public List<String> getEquipmentIds(){
        List<String> result = new ArrayList<>();
        List<EamEquipmentVO> equipments = getCurrentCompanyEquipments();
        for (EamEquipmentVO equipmentVO : equipments){
            result.add(equipmentVO.getEquipmentId());
        }
        return result;
    }
}
