package com.kuyun.eam.vo;

import com.kuyun.eam.dao.model.EamTicket;
import com.kuyun.eam.dao.model.EamTicketType;
import com.kuyun.eam.common.constant.TicketSearchCategory;

public class EamTicketVO extends EamTicket {
	
	private EamTicketType ticketType;
	    
	public EamTicketType getTicketType() {
		return ticketType;
	}

	public void setTicketType(EamTicketType ticketType) {
		this.ticketType = ticketType;
	}

}
