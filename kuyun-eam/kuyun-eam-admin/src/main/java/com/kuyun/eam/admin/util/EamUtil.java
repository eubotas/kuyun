package com.kuyun.eam.admin.util;

import com.kuyun.eam.rpc.api.EamApiService;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.eam.vo.EamProductLineVO;
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

    public List<EamProductLineVO> getCurrentCompanyProductLines(){
        EamProductLineVO productLineVO = new EamProductLineVO();
        productLineVO.setDeleteFlag(Boolean.FALSE);
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            productLineVO.setCompanyId(company.getCompanyId());
        }
        return eamApiService.selectProductLines(productLineVO);
    }

    public List<EamEquipmentVO> getCurrentCompanyEquipments(){
        EamEquipmentVO equipmentVO = new EamEquipmentVO();
        equipmentVO.setDeleteFlag(Boolean.FALSE);
        equipmentVO.setOrderByClause("product_line_id, equipment_category_id desc");
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

    public List<String> getProductLineIds(){
        List<String> result = new ArrayList<>();
        List<EamProductLineVO> productLines = getCurrentCompanyProductLines();
        for (EamProductLineVO productLineVO : productLines){
            result.add(productLineVO.getProductLineId());
        }
        return result;
    }
}
