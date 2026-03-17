package com.ccc.post.dto;

public class PostPageDTO {
    private int page;        // 현재 페이지
    private int rowCount;    // 페이지당 게시글 수
    private int totalCount;  // 전체 게시글 수
    private int startPage;   // 시작 페이지 번호
    private int endPage;     // 끝 페이지 번호
    private boolean prev;    // 이전 버튼 유무
    private boolean next;    // 다음 버튼 유무
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	@Override
	public String toString() {
		return "PostPageDTO [page=" + page + ", rowCount=" + rowCount + ", totalCount=" + totalCount + ", startPage="
				+ startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + "]";
	}
	
	
    
}
