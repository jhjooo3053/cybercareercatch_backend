package com.ccc.post.dto;

public class PostSearchDTO {
	private String keyword;
	private String searchType;
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	@Override
	public String toString() {
		return "PostSearchDTO [keyword=" + keyword + ", searchType=" + searchType + "]";
	}

	
	

}
