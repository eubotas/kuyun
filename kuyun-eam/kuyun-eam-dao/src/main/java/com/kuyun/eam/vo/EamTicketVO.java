package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketType;

public class EamTicketVO extends EamTicket {
	
	private EamTicketType ticketType;
	private String serviceman;
    private String servicePhone;
    private String customerContacts;
    private String customerPhone;

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


}
