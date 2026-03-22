package com.ccc.expo.dto;

public class ExpoDTO {
//   EXPO_NUMBER NUMBER,
//   ADMIN_NUMBER NUMBER NOT NULL,
//   EXPO_NAME VARCHAR2(100) NOT NULL,
//   START_DATE DATE DEFAULT SYSDATE NOT NULL,
//   END_DATE DATE DEFAULT SYSDATE NOT NULL,
//   LOCATION VARCHAR2(200) NOT NULL,
//   REG_DATE DATE DEFAULT SYSDATE NOT NULL
   
      private int expoNumber;
      private int adminNumber;
      private String expoName;
      private String startDate;
      private String endDate;
      private String location;
      private String regDate;
      public int getExpoNumber() {
         return expoNumber;
      }
      public void setExpoNumber(int expoNumber) {
         this.expoNumber = expoNumber;
      }
      public int getAdminNumber() {
         return adminNumber;
      }
      public void setAdminNumber(int adminNumber) {
         this.adminNumber = adminNumber;
      }
      public String getExpoName() {
         return expoName;
      }
      public void setExpoName(String expoName) {
         this.expoName = expoName;
      }
      public String getStartDate() {
         return startDate;
      }
      public void setStartDate(String startDate) {
         this.startDate = startDate;
      }
      public String getEndDate() {
         return endDate;
      }
      public void setEndDate(String endDate) {
         this.endDate = endDate;
      }
      public String getLocation() {
         return location;
      }
      public void setLocation(String location) {
         this.location = location;
      }
      public String getRegDate() {
         return regDate;
      }
      public void setRegDate(String regDate) {
         this.regDate = regDate;
      }
      
      @Override
      public String toString() {
      return "ExpoDTO [expoNumber=" + expoNumber + ", adminNumber=" + adminNumber + ", expoName=" + expoName
            + ", startDate=" + startDate + ", endDate=" + endDate + ", location=" + location + ", regDate="
            + regDate + "]";
      }
      
      
      
}
