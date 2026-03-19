package com.ccc.mypage.dto;
//확장용
public class MypageCommentListDTO {
    private int commentNumber;
    private int postNumber;
    private String postTitle;
    private String commentContent;
    private String commentDate;
	public int getCommentNumber() {
		return commentNumber;
	}
	
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
	
	public String getCommentContent() {
		return commentContent;
	}
	
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	public String getCommentDate() {
		return commentDate;
	}
	
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	
	public void setCommentNumber(int commentNumber) {
		this.commentNumber = commentNumber;
	}

    
    
}
