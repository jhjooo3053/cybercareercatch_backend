package com.ccc.qna.dto;

public class QnaDetailDTO {
   private Long postNumber;
   private String postTitle;
   private String postContent;
   private String memberId;
   private String postCreatedDate;
   private String answerStatus;
   private String companyName;
   
   public QnaDetailDTO() {;}

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

   public String getPostContent() {
      return postContent;
   }

   public void setPostContent(String postContent) {
      this.postContent = postContent;
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

   public String getCompanyName() {
      return companyName;
   }

   public void setCompanyName(String companyName) {
      this.companyName = companyName;
   }

   @Override
   public String toString() {
      return "QnaDetailDTO [postNumber=" + postNumber + ", postTitle=" + postTitle + ", postContent=" + postContent
            + ", memberId=" + memberId + ", postCreatedDate=" + postCreatedDate + ", answerStatus=" + answerStatus
            + ", companyName=" + companyName + "]";
   }
}