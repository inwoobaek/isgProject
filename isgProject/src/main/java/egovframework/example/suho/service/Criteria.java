package egovframework.example.suho.service;


import org.springframework.stereotype.Service;


public class Criteria {
	private int page; //페이지 인덱스
	private int perPageNum; // 페이지 행 갯수

	public int getPageStart() {
		return (this.page -1) * this.perPageNum;
	}
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public int getPage() {
		return page;
	}
	
	//페이지는 1부터 시작하므로 1보다 작은 값일 경우 1로 초기화
	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
		}
		else {
			this.page = page;
		}
	}
	
	public int getPerPageNum() {
		return perPageNum;
	}
	
	public void setPerPageNym(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
		}
		else {
			this.perPageNum = perPageNum;
		}
	}
	
}
