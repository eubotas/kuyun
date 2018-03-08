package com.kuyun.eam.vo;

import java.io.Serializable;
import java.util.Date;

public class EamTicketRejectRecordVO implements Serializable {

	private String userName;
    private String rejectCommont;
    private Date rejectDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRejectCommont() {
        return rejectCommont;
    }

    public void setRejectCommont(String rejectCommont) {
        this.rejectCommont = rejectCommont;
    }

    public Date getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Date rejectDate) {
        this.rejectDate = rejectDate;
    }

}
