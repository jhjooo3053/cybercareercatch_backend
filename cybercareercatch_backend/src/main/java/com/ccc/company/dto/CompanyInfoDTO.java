package com.ccc.company.dto;

public class CompanyInfoDTO {

    /*
     * 회사 기본 정보
     * - companyName, companyAddress 는 TBL_COMPANY
     * - filePath 는 TBL_FILE
     */
    private int companyNumber;
    private String companyName;
    private String companyAddress;
    private String filePath;

    /*
     * 회사 상세 정보
     * - 아래 값들은 TBL_COMP_INFO
     */
    private String compCeoName;
    private int compFndYear;
    private int compEmplCnt;
    private String compRev;
    private String compCap;
    private String compType;
    private String compSummary;
    private String compTech;
    private String compMainBiz;
    private String compInfo;
    private String compSvcHist;
    private String compPageDate;

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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCompCeoName() {
        return compCeoName;
    }

    public void setCompCeoName(String compCeoName) {
        this.compCeoName = compCeoName;
    }

    public int getCompFndYear() {
        return compFndYear;
    }

    public void setCompFndYear(int compFndYear) {
        this.compFndYear = compFndYear;
    }

    public int getCompEmplCnt() {
        return compEmplCnt;
    }

    public void setCompEmplCnt(int compEmplCnt) {
        this.compEmplCnt = compEmplCnt;
    }

    public String getCompRev() {
        return compRev;
    }

    public void setCompRev(String compRev) {
        this.compRev = compRev;
    }

    public String getCompCap() {
        return compCap;
    }

    public void setCompCap(String compCap) {
        this.compCap = compCap;
    }

    public String getCompType() {
        return compType;
    }

    public void setCompType(String compType) {
        this.compType = compType;
    }

    public String getCompSummary() {
        return compSummary;
    }

    public void setCompSummary(String compSummary) {
        this.compSummary = compSummary;
    }

    public String getCompTech() {
        return compTech;
    }

    public void setCompTech(String compTech) {
        this.compTech = compTech;
    }

    public String getCompMainBiz() {
        return compMainBiz;
    }

    public void setCompMainBiz(String compMainBiz) {
        this.compMainBiz = compMainBiz;
    }

    public String getCompInfo() {
        return compInfo;
    }

    public void setCompInfo(String compInfo) {
        this.compInfo = compInfo;
    }

    public String getCompSvcHist() {
        return compSvcHist;
    }

    public void setCompSvcHist(String compSvcHist) {
        this.compSvcHist = compSvcHist;
    }

    public String getCompPageDate() {
        return compPageDate;
    }

    public void setCompPageDate(String compPageDate) {
        this.compPageDate = compPageDate;
    }

    @Override
    public String toString() {
        return "CompanyInfoDTO [companyNumber=" + companyNumber
                + ", companyName=" + companyName
                + ", companyAddress=" + companyAddress
                + ", filePath=" + filePath
                + ", compCeoName=" + compCeoName
                + ", compFndYear=" + compFndYear
                + ", compEmplCnt=" + compEmplCnt
                + ", compRev=" + compRev
                + ", compCap=" + compCap
                + ", compType=" + compType
                + ", compSummary=" + compSummary
                + ", compTech=" + compTech
                + ", compMainBiz=" + compMainBiz
                + ", compInfo=" + compInfo
                + ", compSvcHist=" + compSvcHist
                + ", compPageDate=" + compPageDate + "]";
    }
}