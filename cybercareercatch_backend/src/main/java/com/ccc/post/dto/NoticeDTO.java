package com.ccc.post.dto;

public class NoticeDTO {
	  private int postNumber;
	  private String postType;
	  private int adminNumber;
	  private String adminId;
	  private String postTitle;
	  private String postContent;
	  private String postDate;
	  private String editDate;
	  private int viewCount;
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
	  public int getAdminNumber() {
		  return adminNumber;
	  }
	  public void setAdminNumber(int adminNumber) {
		  this.adminNumber = adminNumber;
	  }
	  public String getAdminId() {
		  return adminId;
	  }
	  public void setAdminId(String adminId) {
		  this.adminId = adminId;
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
		return "NoticeDTO [postNumber=" + postNumber + ", postType=" + postType + ", adminNumber=" + adminNumber
				+ ", adminId=" + adminId + ", postTitle=" + postTitle + ", postContent=" + postContent + ", postDate="
				+ postDate + ", editDate=" + editDate + ", viewCount=" + viewCount + "]";
	  }
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
