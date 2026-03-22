package com.ccc.expo.dto;

public class ExpoCompanyDTO {
//    EXPO_NUMBER    NUMBER,
//    COMPANY_NUMBER NUMBER
      private int expoNumber;
      private int companyNumber;
      
      public int getExpoNumber() {
         return expoNumber;
      }
      public void setExpoNumber(int expoNumber) {
         this.expoNumber = expoNumber;
      }
      public int getCompanyNumber() {
         return companyNumber;
      }
      public void setCompanyNumber(int companyNumber) {
         this.companyNumber = companyNumber;
      }
      
      @Override
      public String toString() {
      return "ExpoCompanyDTO [expoNumber=" + expoNumber + ", companyNumber=" + companyNumber + "]";
      }
      
      
}
