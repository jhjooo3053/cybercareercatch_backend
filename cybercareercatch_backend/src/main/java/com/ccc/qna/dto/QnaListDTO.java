package com.ccc.qna.dto;

public class QnaListDTO {
	private Long postNumber;
	private String postTitle;
	private String memberId;
	private String postCreatedDate;
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
	}

	public String getPostCreatedDate() {
		return postCreatedDate;
	}

	public void setPostCreatedDate(String postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
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
				+ ", postCreatedDate=" + postCreatedDate + ", answerStatus=" + answerStatus + "]";
	}
}