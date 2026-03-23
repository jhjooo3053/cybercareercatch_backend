package com.ccc.qna.dto;

public class QnaDetailDTO {
	private Long postNumber;
	private Long userNumber;
	private Long companyNumber;
	private String postTitle;
	private String postContent;
	private String memberId;
	private String userId;
	private String postCreatedDate;
	private String postDate;
	private int viewCount;
	private String answerStatus;
	private String companyName;

	public QnaDetailDTO() {
		;
	}

	public Long getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(Long postNumber) {
		this.postNumber = postNumber;
	}

	public Long getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Long userNumber) {
		this.userNumber = userNumber;
	}

	public Long getCompanyNumber() {
		return companyNumber;
	}

	public void setCompanyNumber(Long companyNumber) {
		this.companyNumber = companyNumber;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
		this.userId = memberId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
		this.memberId = userId;
	}

	public String getPostCreatedDate() {
		return postCreatedDate;
	}

	public void setPostCreatedDate(String postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
		this.postDate = postCreatedDate;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
		this.postCreatedDate = postDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getAnswerStatus() {
		return answerStatus;
	}

	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "QnaDetailDTO [postNumber=" + postNumber + ", userNumber=" + userNumber + ", companyNumber="
				+ companyNumber + ", postTitle=" + postTitle + ", postContent=" + postContent + ", userId=" + userId
				+ ", postDate=" + postDate + ", viewCount=" + viewCount + ", answerStatus=" + answerStatus
				+ ", companyName=" + companyName + "]";
	}
}