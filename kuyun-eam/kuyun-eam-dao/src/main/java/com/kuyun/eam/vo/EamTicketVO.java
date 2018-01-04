package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketType;

public class EamTicketVO extends EamTicket {
	
	private EamTicketType ticketType;
	private String realname;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	private String phone;
	    
	public EamTicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(EamTicketType ticketType) {
		this.ticketType = ticketType;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

}
