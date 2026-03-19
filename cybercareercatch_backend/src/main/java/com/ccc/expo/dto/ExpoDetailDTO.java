package com.ccc.expo.dto;

public class ExpoDetailDTO {
    private int expoNumber;
    private String expoName;
    private String location;
    private int companyNumber;
    private String companyName;
    private String companyAddress;

    public ExpoDetailDTO() { }

    public int getExpoNumber() {
        return expoNumber;
    }

    public void setExpoNumber(int expoNumber) {
        this.expoNumber = expoNumber;
    }

    public String getExpoName() {
        return expoName;
    }

    public void setExpoName(String expoName) {
        this.expoName = expoName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCompanyNumber() {
        return companyNumber;
    }

    public void setCompanyNumber(int companyNumber) {
        this.companyNumber = companyNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}