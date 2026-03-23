package com.ccc.qna.dto;

public class QnaCommentDTO {
	private Integer commentNumber;
	private Integer postNumber;
	private Integer userNumber;
	private String memberId;
	private String commentContent;
	private String commentCreatedDate;

	public Integer getCommentNumber() {
		return commentNumber;
	}

	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
	}

	public Integer getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(Integer postNumber) {
		this.postNumber = postNumber;
	}

	public Integer getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(Integer userNumber) {
		this.userNumber = userNumber;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentCreatedDate() {
		return commentCreatedDate;
	}

	public void setCommentCreatedDate(String commentCreatedDate) {
		this.commentCreatedDate = commentCreatedDate;
	}
}