package com.kuyun.eam.admin.controller.manager;

import com.google.common.base.Splitter;
import com.kuyun.common.base.BaseController;
import com.kuyun.common.constant.OrgDepartment;
import com.kuyun.eam.common.constant.*;
import com.kuyun.eam.dao.model.*;
import com.kuyun.eam.rpc.api.*;
import com.kuyun.eam.vo.EamEquipmentVO;
import com.kuyun.eam.vo.EamTicketAssessmentTagVO;
import com.kuyun.eam.vo.EamTicketVO;
import com.kuyun.upms.client.util.BaseEntityUtil;
import com.kuyun.upms.common.JspUtil;
import com.kuyun.upms.dao.model.UpmsUserCompany;
import com.kuyun.upms.dao.vo.UpmsOrgUserVo;
import com.kuyun.upms.rpc.api.UpmsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.*;


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
    private EamTicketTagService eamTicketTagService;

    @Autowired
    public com.kuyun.fileuploader.rpc.api.FileUploaderService fileUploaderService;

    @Autowired
    private EamTicketAppointedRecordService eamTicketAppointRecordService;


    public void setTicketInfo(int id, ModelMap modelMap) {
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
		if(eamTicket.getVoicePath() != null) {
			for (String uuid : Splitter.on(',')
					.trimResults()
					.omitEmptyStrings()
					.split(eamTicket.getVoicePath())) {
				voiceList.add(fileUploaderService.getServerInfo().getEndpoint_show() + "/" + uuid);
			}
		}
        modelMap.put("voiceList", voiceList);

        EamTicketRecordExample etre = new EamTicketRecordExample();
        etre.createCriteria().andTicketIdEqualTo(id);
        etre.setOrderByClause("eam_ticket_record_create_time desc");

        List<EamTicketRecord> records = eamTicketRecordService.selectByExample(etre);
        modelMap.put("records", records);

        EamTicketAppointedRecordExample example = new EamTicketAppointedRecordExample();
        example.createCriteria().andTicketIdEqualTo(id).andDeleteFlagEqualTo(Boolean.FALSE);
        example.setOrderByClause("id asc");

        List<EamTicketAppointedRecord> appointedRecordes = eamTicketAppointRecordService.selectByExample(example);
        modelMap.put("appointedRecordes", appointedRecordes);

        setOperatorList(modelMap);
    }

    public void selectTicketUpdate(ModelMap map){
        EamTicketTypeExample typeExample = new EamTicketTypeExample();
        EamTicketTypeExample.Criteria criteria = typeExample.createCriteria();
        criteria.andCompanyIdEqualTo(getCompanyId()).andDeleteFlagEqualTo(Boolean.FALSE);
        List<EamTicketType> types = eamTicketTypeService.selectByExample( typeExample );
        map.put("ticketTypes", JspUtil.getMapList(types,"id","name"));

        EamEquipmentVO equipmentVO = new EamEquipmentVO();
        equipmentVO.setCompanyId(getCompanyId());
        equipmentVO.setDeleteFlag(Boolean.FALSE);
        List<EamEquipmentVO> rows = eamApiService.selectEquipments(equipmentVO);
        map.put("equipments",  JspUtil.getMapList(rows,"equipmentId","name"));
        map.put("uploadServer", fileUploaderService.getServerInfo());
        map.put("ticketPriorityList", JspUtil.getMapList(Arrays.asList(TicketPriority.values()),"code","name"));

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

    protected List<EamTicketTag> getTicketTag(){
        EamTicketTagExample eamTicketTagExample = new EamTicketTagExample();
        EamTicketTagExample.Criteria criteria = eamTicketTagExample.createCriteria();
        criteria.andDeleteFlagEqualTo(Boolean.FALSE);

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();

        if (company != null){
            criteria.andCompanyIdEqualTo(company.getCompanyId());
        }
        List<EamTicketTag> rows = eamTicketTagService.selectByExample(eamTicketTagExample);
        return rows;
    }

    public void setOperatorList(ModelMap modelMap) {
        List<UpmsOrgUserVo> users = getOperatorUsers();
        modelMap.put("users",  JspUtil.getMapList(users,"userId","realname"));
    }

    public List<UpmsOrgUserVo> getOperatorUsers() {
        UpmsOrgUserVo orgUserVo = new UpmsOrgUserVo();

        UpmsUserCompany company = baseEntityUtil.getCurrentUserCompany();
        if (company != null){
            orgUserVo.setCompanyId(company.getCompanyId());
        }
        orgUserVo.setOrgName(OrgDepartment.REPAIR_DEPARTMENT.getName());

        return upmsApiService.selectOrgUsersByOrgNameCompanyId( orgUserVo);
    }
}