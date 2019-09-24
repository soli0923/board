package kr.co.domain;

public class SPageTO<T> extends PageTO<T>{
	private String searchType;
	private String keyword;
	
	public SPageTO() {
		super();
	}

	public SPageTO(int curPage, String searchType, String keyword) {
		super(curPage);
		this.searchType=searchType;
		this.keyword=keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
	
}
