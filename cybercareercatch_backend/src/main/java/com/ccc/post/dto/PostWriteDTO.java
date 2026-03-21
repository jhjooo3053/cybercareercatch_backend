package com.ccc.post.dto;

public class PostWriteDTO {
	private String postType;
	private int userNumber;
	private String postTitle;
	private String postContent;
	
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
	@Override
	public String toString() {
		return "PostWriteDTO [postType=" + postType + ", userNumber=" + userNumber + ", postTitle=" + postTitle
				+ ", postContent=" + postContent + "]";
	}
	
	

	
	
	
}
