package com.kuyun.eam.admin.controller.manager;

import com.google.common.base.Splitter;
import com.kuyun.common.base.BaseController;
import com.kuyun.eam.common.constant.OrgDepartment;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.eam.vo.EamProductLineVO;
import com.kuyun.eam.vo.EamTicketAssessmentTagVO;
import com.kuyun.eam.vo.EamTicketVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.dao.model.UpmsUser;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;


public abstract class EamTicketBaseController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(EamTicketController.class);
    @Autowired
    public EamApiService eamApiService;
    @Autowired
    private BaseEntityUtil baseEntityUtil;
    @Autowired
    public EamTicketTypeService eamTicketTypeService;

    @Autowired
    public EamEquipmentCategoryService eamEquipmentCategoryService;

    @Autowired
    public EamEquipmentService eamEquipmentService;

    @Autowired
    public UpmsApiService upmsApiService;

    @Autowired
    private EamTicketRecordService eamTicketRecordService;

    @Autowired
    public com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;

	public void setTicketInfo( int id, ModelMap modelMap) {
		EamTicketExample ete = new EamTicketExample();
		ete.createCriteria().andTicketIdEqualTo(id);
		EamTicketVO eamTicket = eamApiService.selectTicket(ete).get(0);

        //assement
        Integer assId= eamTicket.getAssessmentId();
        if(assId != null){
            eamTicket.setTagNames(getAssessmentTicketTag(assId));
        }
		modelMap.put("ticket", eamTicket);
		
		//retrieve the image list
		List<String> imageList =  new ArrayList<String>();
		if(eamTicket.getImagePath() != null) {
			for (String uuid : Splitter.on(',')
					.trimResults()
					.omitEmptyStrings()
					.split(eamTicket.getImagePath())) {
				imageList.add(fileUploaderService.getServerInfo().getEndpoint_show() + "/" + uuid);
			}
		}
		modelMap.put("imageList", imageList);

		//retrieve the voice list
		List<String> voiceList =  new ArrayList<String>();
//		if(eamTicket.getVoicePath() != null) {
//			for (String uuid : Splitter.on(',')
//					.trimResults()
//					.omitEmptyStrings()
//					.split(eamTicket.getVoicePath())) {
//				voiceList.add(fileUploaderService.getServerInfo().getEndpoint_show() + "/" + uuid);
//			}
//		}
		modelMap.put("voiceList", voiceList);

        EamTicketRecordExample etre = new EamTicketRecordExample();
		etre.createCriteria().andTicketIdEqualTo(id);
		etre.setOrderByClause("eam_ticket_record_create_time desc");

		List<EamTicketRecord> records = eamTicketRecordService.selectByExample(etre);
		modelMap.put("records", records);
	}

    public void setOperatorList(ModelMap modelMap) {
        List<UpmsOrgUserVo> users = getOperatorUsers();
        modelMap.put("users", users);
    }

    public List<UpmsOrgUserVo> getOperatorUsers() {
        UpmsOrgUserVo orgUserVo = new UpmsOrgUserVo();

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            orgUserVo.setCompanyId(company.getCompanyId());
        }
        orgUserVo.setOrgName(OrgDepartment.MAINTENANCE_DEPARTMENT.getName());

        return upmsApiService.selectOrgUsersByOrgNameCompanyId( orgUserVo);
    }

    public void selectTicketUpdate(ModelMap modelMap){
        List<UpmsUser> users = upmsApiService.selectUsersByUserId(baseEntityUtil.getCurrentUser().getUserId());

        modelMap.put("users", users);
        EamTicketTypeExample typeExample = new EamTicketTypeExample();
        EamTicketTypeExample.Criteria criteria = typeExample.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId());
        List<EamTicketType> types = eamTicketTypeService.selectByExample( typeExample );
        modelMap.put("ticketTypes", types);

        EamProductLineVO productLineVO = new EamProductLineVO();
        productLineVO.setDeleteFlag(Boolean.FALSE);
        productLineVO.setCompanyId(getCompanyId());
        List<EamProductLineVO> productLines = eamApiService.selectProductLines(productLineVO);
        modelMap.addAttribute("productLines", productLines);

        EamEquipmentCategoryExample example = new EamEquipmentCategoryExample();
        EamEquipmentCategoryExample.Criteria criteria2 = example.createCriteria();
        criteria2.andCompanyIdEqualTo(getCompanyId());
        List<EamEquipmentCategory> cats = eamEquipmentCategoryService.selectByExample( example );
        modelMap.addAttribute("equipmentCategorys", cats);

        EamEquipmentVO equipmentVO = new EamEquipmentVO();
        equipmentVO.setCompanyId(getCompanyId());
        List<EamEquipmentVO> rows = eamApiService.selectEquipments(equipmentVO);
        modelMap.addAttribute("equipments", rows);

        modelMap.put("uploadServer", fileUploaderService.getServerInfo());
    }

    public int getCurrUserId(){
		return baseEntityUtil.getCurrentUser().getUserId();
	}

	public int getCompanyId(){
	    int cId=-1;
        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            cId = company.getCompanyId();
        }
        return cId;
    }

    protected String getAssessmentTicketTag(int assessId){
        EamTicketAssessmentTagVO eamTicketTag = new EamTicketAssessmentTagVO();
        eamTicketTag.setAssessmentId(assessId);
        List<EamTicketAssessmentTagVO> rows = eamApiService.selectTicketAssessmentTags(eamTicketTag);
        String names="";
        for(EamTicketAssessmentTagVO vo: rows){
            names +=vo.getTagName()+", ";
        }
        if(names.endsWith(", "))
            names= names.substring(0, names.length()-2);
        return names;
    }
}