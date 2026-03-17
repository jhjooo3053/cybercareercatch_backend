package com.ccc.post.dto;

public class PostDTO {
    private int postNumber;
    private String postType;
    private int userNumber;
    private int adminNumber;
    private int companyNumber;
    private String answerStatus;
    private String postTitle;
    private String postContent;
    private String postDate;
    private int viewCount;
    private String editDate;
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
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}
	public int getCompanyNumber() {
		return companyNumber;
	}
	public void setCompanyNumber(int companyNumber) {
		this.companyNumber = companyNumber;
	}
	public String getAnswerStatus() {
		return answerStatus;
	}
	public void setAnswerStatus(String answerStatus) {
		this.answerStatus = answerStatus;
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
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	@Override
	public String toString() {
		return "PostDTO [postNumber=" + postNumber + ", postType=" + postType + ", userNumber=" + userNumber
				+ ", adminNumber=" + adminNumber + ", companyNumber=" + companyNumber + ", answerStatus=" + answerStatus
				+ ", postTitle=" + postTitle + ", postContent=" + postContent + ", postDate=" + postDate
				+ ", viewCount=" + viewCount + ", editDate=" + editDate + "]";
	}
	
	
}
