package com.ccc.expo.dto;

import java.sql.Date;

public class ExpoCalendarDTO {
    private int expoNumber;
    private String expoName;
    private Date startDate;
    private Date endDate;

    public ExpoCalendarDTO() { }

    public int getExpoNumber() {
        return expoNumber;
    }

    public void setExpoNumber(int expoNumber) {
        this.expoNumber = expoNumber;
    }

    public String getExpoName() {
        return expoName;
    }

    public void setExpoName(String expoName) {
        this.expoName = expoName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}