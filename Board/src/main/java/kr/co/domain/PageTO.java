package kr.co.domain;

import java.util.List;

public class PageTO<T> {
	private int amount;
	private int curPage;
	private int perPage;
	private int totalPage;
	private int startNum;
	private int endNum;
	private List<T> list;
	
	private int perLine = 10;
	private int bpn;
	private int spn;
	
	public PageTO() {
		curPage = 1;
		perPage = 10;
	}
	
	public PageTO(int curPage) {
		this.curPage = curPage;
		perPage = 10;
	}

	
	private void change() {
		totalPage = (this.amount-1)/perPage +1;
		
		startNum = (curPage - 1)*perPage +1;
		endNum = curPage * perPage;
		endNum = endNum < this.amount ? endNum : this.amount;
		
		bpn = ((curPage-1)/perLine )*perLine  +1;
		spn = ((curPage-1)/perLine +1) *perLine ;
		spn = spn < totalPage ? spn : totalPage;
	}

	
	
	
	public int getPerLine() {
		return perLine;
	}

	public void setPerLine(int perLine) {
		this.perLine = perLine;
		change();
	}

	public int getBpn() {
		return bpn;
	}

	public void setBpn(int bpn) {
		this.bpn = bpn;
	}

	public int getSpn() {
		return spn;
	}

	public void setSpn(int spn) {
		this.spn = spn;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		
		change();
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
				
		change();
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		
		change();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
