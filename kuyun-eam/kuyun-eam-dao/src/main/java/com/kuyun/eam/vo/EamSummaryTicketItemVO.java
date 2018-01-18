package com.kuyun.eam.vo;

import java.io.Serializable;

public class EamSummaryTicketItemVO implements Serializable {

	private String totalStatusName;
    private String noAppointStatusName;
    private String processingStatusName;
    private String notResolvedStatusName;
    private String resolvedStatusName;
    private Integer totalTicketCount;
    private Integer noAppointTicketCount;
    private Integer processingTicketCount;
    private Integer notResolvedTicketCount;
    private Integer resolvedTicketCount;


    public String getTotalStatusName() {
        return totalStatusName;
    }

    public void setTotalStatusName(String totalStatusName) {
        this.totalStatusName = totalStatusName;
    }

    public String getNoAppointStatusName() {
        return noAppointStatusName;
    }

    public void setNoAppointStatusName(String noAppointStatusName) {
        this.noAppointStatusName = noAppointStatusName;
    }

    public String getProcessingStatusName() {
        return processingStatusName;
    }

    public void setProcessingStatusName(String processingStatusName) {
        this.processingStatusName = processingStatusName;
    }

    public String getNotResolvedStatusName() {
        return notResolvedStatusName;
    }

    public void setNotResolvedStatusName(String notResolvedStatusName) {
        this.notResolvedStatusName = notResolvedStatusName;
    }

    public String getResolvedStatusName() {
        return resolvedStatusName;
    }

    public void setResolvedStatusName(String resolvedStatusName) {
        this.resolvedStatusName = resolvedStatusName;
    }

    public Integer getTotalTicketCount() {
        return totalTicketCount;
    }

    public void setTotalTicketCount(Integer totalTicketCount) {
        this.totalTicketCount = totalTicketCount;
    }

    public Integer getNoAppointTicketCount() {
        return noAppointTicketCount;
    }

    public void setNoAppointTicketCount(Integer noAppointTicketCount) {
        this.noAppointTicketCount = noAppointTicketCount;
    }

    public Integer getProcessingTicketCount() {
        return processingTicketCount;
    }

    public void setProcessingTicketCount(Integer processingTicketCount) {
        this.processingTicketCount = processingTicketCount;
    }

    public Integer getNotResolvedTicketCount() {
        return notResolvedTicketCount;
    }

    public void setNotResolvedTicketCount(Integer notResolvedTicketCount) {
        this.notResolvedTicketCount = notResolvedTicketCount;
    }

    public Integer getResolvedTicketCount() {
        return resolvedTicketCount;
    }

    public void setResolvedTicketCount(Integer resolvedTicketCount) {
        this.resolvedTicketCount = resolvedTicketCount;
    }

}
