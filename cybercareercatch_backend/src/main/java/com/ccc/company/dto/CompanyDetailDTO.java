package com.ccc.company.dto;

public class CompanyDetailDTO {
	
	private int companyNumber;
	private String companyName;
	private String companyAddress;
	private String companyState;
	private String companyBrn;
	private String filePath;
	private String compCeoName;
	private int compFndYear;


	/*
	 * NUMBER / NUMBER(15) 컬럼은 int로 받으면 오버플로우가 날 수 있어서
	 * long으로 변경한다.
	 */
	private long compEmplCnt;
	private String compRev;
	private String compCap;

	private String compType;
	private String compSummary;
	private String compTech;
	private String compMainBiz;
	private String compInfo;
	private String compSvcHist;
	private String jobPostProfile;
	private String jobPostContent;
	private String jobPostProcess;
	private String jobPostMethod;
	private String jobPostDate;
	private int cat1IsHiring;
	private int cat2IsHiring;
	private int cat3IsHiring;
	private int cat4IsHiring;
	
	
	public int getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(int companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getCompanyState() {
		return companyState;
	}
	public void setCompanyState(String companyState) {
		this.companyState = companyState;
	}
	public String getCompanyBrn() {
		return companyBrn;
	}
	public void setCompanyBrn(String companyBrn) {
		this.companyBrn = companyBrn;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCompCeoName() {
		return compCeoName;
	}
	public void setCompCeoName(String compCeoName) {
		this.compCeoName = compCeoName;
	}
	public int getCompFndYear() {
		return compFndYear;
	}
	public void setCompFndYear(int compFndYear) {
		this.compFndYear = compFndYear;
	}
	public long getCompEmplCnt() {
		return compEmplCnt;
	}
	public void setCompEmplCnt(long compEmplCnt) {
		this.compEmplCnt = compEmplCnt;
	}
	public String getCompRev() {
		return compRev;
	}
	public void setCompRev(String compRev) {
		this.compRev = compRev;
	}
	public String getCompCap() {
		return compCap;
	}
	public void setCompCap(String compCap) {
		this.compCap = compCap;
	}
	public String getCompType() {
		return compType;
	}
	public void setCompType(String compType) {
		this.compType = compType;
	}
	public String getCompSummary() {
		return compSummary;
	}
	public void setCompSummary(String compSummary) {
		this.compSummary = compSummary;
	}
	public String getCompTech() {
		return compTech;
	}
	public void setCompTech(String compTech) {
		this.compTech = compTech;
	}
	public String getCompMainBiz() {
		return compMainBiz;
	}
	public void setCompMainBiz(String compMainBiz) {
		this.compMainBiz = compMainBiz;
	}
	public String getCompInfo() {
		return compInfo;
	}
	public void setCompInfo(String compInfo) {
		this.compInfo = compInfo;
	}
	public String getCompSvcHist() {
		return compSvcHist;
	}
	public void setCompSvcHist(String compSvcHist) {
		this.compSvcHist = compSvcHist;
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
	
	@Override
	public String toString() {
		return "CompanyDetailDTO [companyNumber=" + companyNumber + ", companyName=" + companyName + ", companyAddress="
				+ companyAddress + ", companyState=" + companyState + ", companyBrn=" + companyBrn + ", filePath="
				+ filePath + ", compCeoName=" + compCeoName + ", compFndYear=" + compFndYear + ", compEmplCnt="
				+ compEmplCnt + ", compRev=" + compRev + ", compCap=" + compCap + ", compType=" + compType
				+ ", compSummary=" + compSummary + ", compTech=" + compTech + ", compMainBiz=" + compMainBiz
				+ ", compInfo=" + compInfo + ", compSvcHist=" + compSvcHist + ", jobPostProfile=" + jobPostProfile
				+ ", jobPostContent=" + jobPostContent + ", jobPostProcess=" + jobPostProcess + ", jobPostMethod="
				+ jobPostMethod + ", jobPostDate=" + jobPostDate + ", cat1IsHiring=" + cat1IsHiring + ", cat2IsHiring="
				+ cat2IsHiring + ", cat3IsHiring=" + cat3IsHiring + ", cat4IsHiring=" + cat4IsHiring + "]";
	}

	

	


}