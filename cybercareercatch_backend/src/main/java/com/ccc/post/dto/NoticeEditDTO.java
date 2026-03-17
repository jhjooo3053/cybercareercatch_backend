package com.ccc.post.dto;

public class NoticeEditDTO {
	  private int postNumber;
	  private String postTitle;
	  private String postContent;
	  private String editDate;
	  
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
	  public String getPostContent() {
		  return postContent;
	  }
	  public void setPostContent(String postContent) {
		  this.postContent = postContent;
	  }
	  public String getEditDate() {
		  return editDate;
	  }
	  public void setEditDate(String editDate) {
		  this.editDate = editDate;
	  }
	  @Override
	  public String toString() {
		return "NoticeEditDTO [postNumber=" + postNumber + ", postTitle=" + postTitle + ", postContent=" + postContent
				+ ", editDate=" + editDate + "]";
	  }
	
	  
	   
	  
	  
}