package com.ccc.qna.dto;

public class QnaListDTO {
	private Long postNumber;
	private String postTitle;
	private String memberId;
	private String userId;
	private String companyName;
	private String postCreatedDate;
	private String postDate;
	private int viewCount;
	private String answerStatus;

	public QnaListDTO() {;}

	public Long getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(Long postNumber) {
		this.postNumber = postNumber;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	@Override
	public String toString() {
		return "QnaListDTO [postNumber=" + postNumber + ", postTitle=" + postTitle + ", memberId=" + memberId
				+ ", companyName=" + companyName + ", postDate=" + postDate + ", viewCount=" + viewCount
				+ ", answerStatus=" + answerStatus + "]";
	}
}