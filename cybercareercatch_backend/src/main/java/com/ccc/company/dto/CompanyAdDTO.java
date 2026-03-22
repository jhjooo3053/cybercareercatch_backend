package com.ccc.company.dto; // 회사 DTO 패키지이다.

public class CompanyAdDTO { // 기업홍보 목록 페이지의 페이징 정보를 담는 DTO 클래스이다.

	private int page; // 현재 페이지 번호를 저장하는 필드이다.
	private int rowCount; // 한 페이지에 보여줄 카드 개수를 저장하는 필드이다.
	private int totalCount; // 전체 기업 카드 개수를 저장하는 필드이다.
	private int startPage; // 페이지네이션 시작 번호를 저장하는 필드이다.
	private int endPage; // 페이지네이션 끝 번호를 저장하는 필드이다.
	private boolean prev; // 이전 페이지 구간 존재 여부를 저장하는 필드이다.
	private boolean next; // 다음 페이지 구간 존재 여부를 저장하는 필드이다.

	public int getPage() { // 현재 페이지 번호를 반환하는 getter 메소드이다.
		return page; // page 값을 반환한다.
	}

	public void setPage(int page) { // 현재 페이지 번호를 저장하는 setter 메소드이다.
		this.page = page; // 전달받은 page 값을 필드에 저장한다.
	}

	public int getRowCount() { // 한 페이지당 카드 개수를 반환하는 getter 메소드이다.
		return rowCount; // rowCount 값을 반환한다.
	}

	public void setRowCount(int rowCount) { // 한 페이지당 카드 개수를 저장하는 setter 메소드이다.
		this.rowCount = rowCount; // 전달받은 rowCount 값을 필드에 저장한다.
	}

	public int getTotalCount() { // 전체 기업 수를 반환하는 getter 메소드이다.
		return totalCount; // totalCount 값을 반환한다.
	}

	public void setTotalCount(int totalCount) { // 전체 기업 수를 저장하는 setter 메소드이다.
		this.totalCount = totalCount; // 전달받은 totalCount 값을 필드에 저장한다.
	}

	public int getStartPage() { // 시작 페이지 번호를 반환하는 getter 메소드이다.
		return startPage; // startPage 값을 반환한다.
	}

	public void setStartPage(int startPage) { // 시작 페이지 번호를 저장하는 setter 메소드이다.
		this.startPage = startPage; // 전달받은 startPage 값을 필드에 저장한다.
	}

	public int getEndPage() { // 끝 페이지 번호를 반환하는 getter 메소드이다.
		return endPage; // endPage 값을 반환한다.
	}

	public void setEndPage(int endPage) { // 끝 페이지 번호를 저장하는 setter 메소드이다.
		this.endPage = endPage; // 전달받은 endPage 값을 필드에 저장한다.
	}

	public boolean isPrev() { // 이전 페이지 구간 존재 여부를 반환하는 getter 메소드이다.
		return prev; // prev 값을 반환한다.
	}

	public void setPrev(boolean prev) { // 이전 페이지 구간 존재 여부를 저장하는 setter 메소드이다.
		this.prev = prev; // 전달받은 prev 값을 필드에 저장한다.
	}

	public boolean isNext() { // 다음 페이지 구간 존재 여부를 반환하는 getter 메소드이다.
		return next; // next 값을 반환한다.
	}

	public void setNext(boolean next) { // 다음 페이지 구간 존재 여부를 저장하는 setter 메소드이다.
		this.next = next; // 전달받은 next 값을 필드에 저장한다.
	}

	@Override
	public String toString() { // DTO 상태를 문자열로 확인하기 위한 메소드이다.
		return "CompanyAdDTO [page=" + page + ", rowCount=" + rowCount + ", totalCount=" + totalCount
				+ ", startPage=" + startPage + ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + "]"; // DTO 상태를 문자열로 반환한다.
	}
}