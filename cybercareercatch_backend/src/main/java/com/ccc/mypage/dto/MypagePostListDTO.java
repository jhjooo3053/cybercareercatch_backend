package com.ccc.mypage.dto;

public class MypagePostListDTO {
	
	private int postNumber;
	private String postType;
	private String postTitle;
	private String companyName;
	private String answerStatus;
	private String postDate;
	private Long viewCount;
	
		
	public int getPostNumber() {
		return postNumber;
	}


	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}


	public String getPostType() {
		return postType;
	}


	public void setPostType(String postType) {
		this.postType = postType;
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


	public String getAnswerStatus() {
		return answerStatus;
	}


	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}


	public String getPostDate() {
		return postDate;
	}


	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}


	public Long getViewCount() {
		return viewCount;
	}


	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}



	
}
