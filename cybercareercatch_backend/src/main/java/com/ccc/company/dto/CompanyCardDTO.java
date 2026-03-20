package com.ccc.company.dto;

/*
 * 관리자 기업 정보페이지 목록에서 사용하는 DTO이다.
 */
public class CompanyCardDTO {

    // =========================
    // 기업 카드 / 기업 홍보 목록 출력용 필드
    // =========================
	
	private String sortType; // 정렬 방식을 저장하는 필드이다.

    // 기업 번호
    private int companyNumber;

    // 기업 이름
    private String companyName;

    // 기업 주소
    private String companyAddress;

    // 기업 승인 상태
    private String companyState;

    // 기업 요약 소개
    private String compSummary;

    // 기업 유형
    private String compType;

    // 기업 대표 이미지 경로
    private String filePath;

    // 기업 페이지 등록일
    private String compPageDate;

    // 직군별 채용 여부
    private int cat1IsHiring;
    private int cat2IsHiring;
    private int cat3IsHiring;
    private int cat4IsHiring;

    // =========================
    // 기업 광고 / 목록 조회용 필드
    // =========================

    // 직군 필터 번호
    // 지금 기업홍보 목록 쿼리에서 안 쓸 수도 있지만,
    // 다른 목록 조회 쿼리와 같이 재사용할 가능성이 있으면 남겨둬도 된다.
    private Integer jobNumber;

    // 페이징 시작 행 번호
    private Integer startRow;

    // 페이징 끝 행 번호
    private Integer endRow;

    // 기본 생성자
    public CompanyCardDTO() {;}

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

    public String getCompanyState() {
        return companyState;
    }

    public void setCompanyState(String companyState) {
        this.companyState = companyState;
    }

    public String getCompSummary() {
        return compSummary;
    }

    public void setCompSummary(String compSummary) {
        this.compSummary = compSummary;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCompPageDate() {
        return compPageDate;
    }

    public void setCompPageDate(String compPageDate) {
        this.compPageDate = compPageDate;
    }

    public int getCat1IsHiring() {
        return cat1IsHiring;
    }

    public void setCat1IsHiring(int cat1IsHiring) {
        this.cat1IsHiring = cat1IsHiring;
    }

    public int getCat2IsHiring() {
        return cat2IsHiring;
    }

    public void setCat2IsHiring(int cat2IsHiring) {
        this.cat2IsHiring = cat2IsHiring;
    }

    public int getCat3IsHiring() {
        return cat3IsHiring;
    }

    public void setCat3IsHiring(int cat3IsHiring) {
        this.cat3IsHiring = cat3IsHiring;
    }

    public int getCat4IsHiring() {
        return cat4IsHiring;
    }

    public void setCat4IsHiring(int cat4IsHiring) {
        this.cat4IsHiring = cat4IsHiring;
    }

    public Integer getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(Integer jobNumber) {
        this.jobNumber = jobNumber;
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public String getSortType() { // 정렬 방식을 반환하는 getter 메소드이다.
    	return sortType; // sortType 값을 반환한다.
    }

    public void setSortType(String sortType) { // 정렬 방식을 저장하는 setter 메소드이다.
    	this.sortType = sortType; // 전달받은 sortType 값을 필드에 저장한다.
    }
    
    public String toString() {
        return "CompanyCardDTO [companyNumber=" + companyNumber
                + ", companyName=" + companyName
                + ", companyAddress=" + companyAddress
                + ", companyState=" + companyState
                + ", compSummary=" + compSummary
                + ", filePath=" + filePath
                + ", compPageDate=" + compPageDate
                + ", cat1IsHiring=" + cat1IsHiring
                + ", cat2IsHiring=" + cat2IsHiring
                + ", cat3IsHiring=" + cat3IsHiring
                + ", cat4IsHiring=" + cat4IsHiring
                + ", jobNumber=" + jobNumber
                + ", sortType=" + sortType
                + ", startRow=" + startRow
                + ", endRow=" + endRow + "]";
        
        
    }
}