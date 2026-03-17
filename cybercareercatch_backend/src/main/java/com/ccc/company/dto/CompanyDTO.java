package com.ccc.company.dto;

public class CompanyDTO {
	private int companyNumber;
	private String companyBrn;
	private String companyName;
	private String companyAddress;
	private String companyState;
	public int getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(int companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getCompanyBrn() {
		return companyBrn;
	}
	public void setCompanyBrn(String companyBrn) {
		this.companyBrn = companyBrn;
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
	@Override
	public String toString() {
		return "CompanyDTO [companyNumber=" + companyNumber + ", companyBrn=" + companyBrn + ", companyName="
				+ companyName + ", companyAddress=" + companyAddress + ", companyState=" + companyState + "]";
	}

	
	
}