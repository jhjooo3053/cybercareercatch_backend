package com.ccc.mypage.dto;

public class MypageQnaListDTO {
	private int postNumber;
	private String postTitle;
	private String companyName;
	private String userId;
	private String postDate;
	private String answerStatus;
	public int getPostNumber() {
		return postNumber;
	}
	
	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}
	
	public String getPostTitle() {
		return postTitle;
	}
	
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPostDate() {
		return postDate;
	}
	
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	
	public String getAnswerStatus() {
		return answerStatus;
	}
	
	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}
	
	
}
