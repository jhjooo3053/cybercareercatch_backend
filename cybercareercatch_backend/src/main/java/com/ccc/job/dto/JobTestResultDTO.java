package com.ccc.job.dto;

public class JobTestResultDTO {
	private int jobResultNumber;
	private int userNumber;
	private String userName;
	private int jobNumber;
	private String jobName;
	private String jobResultStatus;
	private String jobResultCreatedDate;
	private String jobResultConfirmDate;

	public JobTestResultDTO() {;}

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
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
		return "JobTestResultDTO [jobResultNumber=" + jobResultNumber + ", userNumber=" + userNumber
				+ ", userName=" + userName + ", jobNumber=" + jobNumber + ", jobName=" + jobName
				+ ", jobResultStatus=" + jobResultStatus + ", jobResultCreatedDate=" + jobResultCreatedDate
				+ ", jobResultConfirmDate=" + jobResultConfirmDate + "]";
	}
}