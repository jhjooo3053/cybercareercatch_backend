package com.ccc.company.dto;

public class JobPostDTO {
	   private int companyNumber;
	   private String jobPostProfile;
	   private String jobPostContent;
	   private String jobPostProcess;
	   private String jobPostMethod;
	   private String jobPostDate;
	   private int cat1IsHiring;
	   private int cat2IsHiring;
	   private int cat3IsHiring;
	   private int cat4IsHiring;
	   private String jobPostEditDate;
	   public int getCompanyNumber() {
		   return companyNumber;
	   }
	   public void setCompanyNumber(int companyNumber) {
		   this.companyNumber = companyNumber;
	   }
	   public String getJobPostProfile() {
		   return jobPostProfile;
	   }
	   public void setJobPostProfile(String jobPostProfile) {
		   this.jobPostProfile = jobPostProfile;
	   }
	   public String getJobPostContent() {
		   return jobPostContent;
	   }
	   public void setJobPostContent(String jobPostContent) {
		   this.jobPostContent = jobPostContent;
	   }
	   public String getJobPostProcess() {
		   return jobPostProcess;
	   }
	   public void setJobPostProcess(String jobPostProcess) {
		   this.jobPostProcess = jobPostProcess;
	   }
	   public String getJobPostMethod() {
		   return jobPostMethod;
	   }
	   public void setJobPostMethod(String jobPostMethod) {
		   this.jobPostMethod = jobPostMethod;
	   }
	   public String getJobPostDate() {
		   return jobPostDate;
	   }
	   public void setJobPostDate(String jobPostDate) {
		   this.jobPostDate = jobPostDate;
	   }
	   public int getCat1IsHiring() {
		   return cat1IsHiring;
	   }
	   public void setCat1IsHiring(int cat1IsHiring) {
		   this.cat1IsHiring = cat1IsHiring;
	   }
	   public int getCat2IsHiring() {
		   return cat2IsHiring;
	   }
	   public void setCat2IsHiring(int cat2IsHiring) {
		   this.cat2IsHiring = cat2IsHiring;
	   }
	   public int getCat3IsHiring() {
		   return cat3IsHiring;
	   }
	   public void setCat3IsHiring(int cat3IsHiring) {
		   this.cat3IsHiring = cat3IsHiring;
	   }
	   public int getCat4IsHiring() {
		   return cat4IsHiring;
	   }
	   public void setCat4IsHiring(int cat4IsHiring) {
		   this.cat4IsHiring = cat4IsHiring;
	   }
	   public String getJobPostEditDate() {
		   return jobPostEditDate;
	   }
	   public void setJobPostEditDate(String jobPostEditDate) {
		   this.jobPostEditDate = jobPostEditDate;
	   }
	   
	   @Override
	   public String toString() {
		return "JobPostDTO [companyNumber=" + companyNumber + ", jobPostProfile=" + jobPostProfile + ", jobPostContent="
				+ jobPostContent + ", jobPostProcess=" + jobPostProcess + ", jobPostMethod=" + jobPostMethod
				+ ", jobPostDate=" + jobPostDate + ", cat1IsHiring=" + cat1IsHiring + ", cat2IsHiring=" + cat2IsHiring
				+ ", cat3IsHiring=" + cat3IsHiring + ", cat4IsHiring=" + cat4IsHiring + ", jobPostEditDate="
				+ jobPostEditDate + "]";
	   }
	   

}
