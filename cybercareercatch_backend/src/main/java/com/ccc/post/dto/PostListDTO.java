package com.ccc.post.dto;

public class PostListDTO {
	private int postNumber;
	private String postTitle;
	private String userId;
	private String postDate;
	private String editDate;
	private int viewCount;

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

	public String getEditDate() {
		return editDate;
	}

	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	@Override
	public String toString() {
		return "PostListDTO [postNumber=" + postNumber + ", postTitle=" + postTitle + ", userId=" + userId
				+ ", postDate=" + postDate + ", editDate=" + editDate + ", viewCount=" + viewCount + "]";
	}
}