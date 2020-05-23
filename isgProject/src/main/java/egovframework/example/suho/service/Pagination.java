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
	private int startPage; // 페이지 행의 시작 번호
	private int endPage; // 페이지 행의 끝 번호
	private boolean prev; // 이전 페이지가 있는 경우
	private boolean next; // 다음 페이지가 있는 경우

	private int displayPageNum = 10;
	private int tempEndPage;

	private static String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm&date=";

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		this.tempEndPage = tempEndPage;

		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}

		prev = startPage == 1 ? false : true;// 1페이지면 이전 누를 수 없게 false
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

		return maxPage;
	}

	// 페이지, 날짜 파라미터
	public static String getUrl(int PAGE) {
		Date d = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");

		return day.format(d) + "&page=" + PAGE;
	}
	
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page", page).build();
		
		return uriComponents.toUriString();
	}
	

}
