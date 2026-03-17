package com.ccc.job.dto;

public class JobResultDTO {

    private int jobResultNumber;
    private int userNumber;
    private int jobNumber;
    private String jobResultStatus;
    private String jobResultCreatedDate;
    private String jobResultConfirmDate;

    public JobResultDTO() {}

    public int getJobResultNumber() {
        return jobResultNumber;
    }

    public void setJobResultNumber(int jobResultNumber) {
        this.jobResultNumber = jobResultNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobResultStatus() {
        return jobResultStatus;
    }

    public void setJobResultStatus(String jobResultStatus) {
        this.jobResultStatus = jobResultStatus;
    }

    public String getJobResultCreatedDate() {
        return jobResultCreatedDate;
    }

    public void setJobResultCreatedDate(String jobResultCreatedDate) {
        this.jobResultCreatedDate = jobResultCreatedDate;
    }

    public String getJobResultConfirmDate() {
        return jobResultConfirmDate;
    }

    public void setJobResultConfirmDate(String jobResultConfirmDate) {
        this.jobResultConfirmDate = jobResultConfirmDate;
    }

	@Override
	public String toString() {
		return "JobResultDTO [jobResultNumber=" + jobResultNumber + ", userNumber=" + userNumber + ", jobNumber="
				+ jobNumber + ", jobResultStatus=" + jobResultStatus + ", jobResultCreatedDate=" + jobResultCreatedDate
				+ ", jobResultConfirmDate=" + jobResultConfirmDate + "]";
	}
    
    
    
}
