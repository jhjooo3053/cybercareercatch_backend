package com.ccc.qna.dto;

public class QnaCommentDTO {
   private Long commentNumber;
   private Long postNumber;
   private Long userNumber;
   private String memberId;
   private String commentContent;
   private String commentCreatedDate;

   
   public QnaCommentDTO() {;}

   public Long getCommentNumber() {
      return commentNumber;
   }

   public void setCommentNumber(Long commentNumber) {
      this.commentNumber = commentNumber;
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

   public void setUserNumber(Long i) {
      this.userNumber = i;
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