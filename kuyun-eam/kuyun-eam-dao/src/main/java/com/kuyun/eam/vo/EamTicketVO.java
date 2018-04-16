package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketType;

public class EamTicketVO extends EamTicket {
	
	private EamTicketType ticketType;
	private String serviceman;
    private String servicePhone;
    private String customerContacts;
    private String customerPhone;

    private Integer assessmentId;
    private String tagNames;
    private Integer assessmentLevel;
    private String assessmentDescription;
    private String productLineName;
    private String equipmentName;

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getServiceman() {
        return serviceman;
    }

    public void setServiceman(String serviceman) {
        this.serviceman = serviceman;
    }

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public String getCustomerContacts() {
        return customerContacts;
    }

    public void setCustomerContacts(String customerContacts) {
        this.customerContacts = customerContacts;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

	public EamTicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(EamTicketType ticketType) {
		this.ticketType = ticketType;
	}

    public String getAssessmentDescription() {
        return assessmentDescription;
    }

    public void setAssessmentDescription(String assessmentDescription) {
        this.assessmentDescription = assessmentDescription;
    }

    public Integer getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Integer assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getTagNames() {
        return tagNames;
    }

    public void setTagNames(String tagNames) {
        this.tagNames = tagNames;
    }

    public Integer getAssessmentLevel() {
        return assessmentLevel;
    }

    public void setAssessmentLevel(Integer assessmentLevel) {
        this.assessmentLevel = assessmentLevel;
    }


}
