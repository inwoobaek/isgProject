package egovframework.example.suho.service;

import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.text.SimpleDateFormat;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class Pagination extends Criteria {

	private Criteria cri; // page, perPageNum 초기값

	private int totalCount;
	private int startPage; // 보여지는 페이지의 시작 번호 (ex. 1 or 11 or 21 ...)
	private int endPage; // 보여지는 페이지의 마지막 번호  (ex. 10 or 20 or 30 ...)
	private boolean prev; // 이전 페이지가 있는 경우
	private boolean next; // 다음 페이지가 있는 경우

	private int displayPageNum = 10; // 화면 하단에 보여지는 페이지의 수
	private int tempEndPage;

	private static String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm&date=";

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	
	public void calcData() {
		
		//올림 함수를 이용하여 endPage를 계산
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

		// 마지막 페이지에서 
		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		this.tempEndPage = tempEndPage;

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		// 1페이지면 이전버튼이 생성되지 않도록 
		prev = startPage == 1 ? false : true;
		
		//마지막 페이지*보여지는 게시물수가 총 게시물 수보다 적거나 많을때 생성되는 경우
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;

	}

	public Criteria getCri() {
		return cri;
	}

	public int getTempEndPage() {
		return tempEndPage;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getTotalCount() {
		return totalCount;
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

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	// 마지막 페이지를 구하는 메소드
	public int getMaxPage() throws IOException {

		int idx = 0;
		int pages = 1;
		int j = 0;
		int maxPage = 0;

		while (true) {
			Document doc = Jsoup.connect(url + getUrl(pages)).get();
			Elements elements = doc.select(".content .paging strong");

			for (Element e : elements) {
				maxPage = Integer.parseInt(e.text());
				if (maxPage == idx) {
					j = 1;
					break;
				}
				idx = maxPage;
			}
			pages++;
			if (j == 1) {
				break;
			}
		}
		//System.out.println("마지막 페이지 : " + maxPage);

		return maxPage;
	}
	
	// base url에서 날짜 파라미터와 페이지넘버 파라미터를 설정하여 리턴해주는 메소드
	public static String getUrl(int PAGE) {
		Date d = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");

		return day.format(d) + "&page=" + PAGE;
	}
	
	// 쿼리 문자열을 생성해주는 메소드
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page).build();
		
		return uriComponents.toUriString();
	}
	

}
