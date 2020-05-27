package egovframework.example.suho.service;


import org.springframework.stereotype.Service;


public class Criteria {
	private int page; //페이지 넘버
	private int perPageNum; // 한장의 페이지에 나오는 게시물의 수

	public int getPageStart() {
		return (this.page -1) * this.perPageNum;
	}
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 20;
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
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
		}
		else {
			this.perPageNum = perPageNum;
		}
	}
	
}
