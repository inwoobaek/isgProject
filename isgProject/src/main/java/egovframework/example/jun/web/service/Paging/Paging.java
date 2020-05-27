package egovframework.example.jun.web.service.Paging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @Class Name : Paging.java
 * @Description : Paging Class
 * @Modification Information
 * @
 * @  수정일                    수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.05.27     이병준            코멘트 작성
 *
 * @author 인실리코젠 BS팀
 * @since 2020.05.27
 */

public class Paging {
	/** URL */
	private static String new_URL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm";
	
	
	private int totalCount;          // 총 게시물
	private int startPage;           // 시작 페이지
	private int endPage;             // 끝 페이지
	private boolean prev;            // 이전
	private boolean next;            // 다음
	private int displayPageNum = 10; // 보여질 페이지목록 개수 
	private Criteria cri;            // 페이지 기준

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	/**
	 * 페이지 기준 데이터
	 * @param -
	 * @return -
	 * @exception -
	 * @comment
	 *  기준에 따라서 '이전', '다음'을 생성 
	 */
	private void calcData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;

		int tempEndPage = (int) (Math.ceil(totalCount / (double) cri.getPerPageNum()));
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}

	/**
	 * URL 생성(page=?)
	 * @param page
	 * @return String uriComponents.toUriString()
	 * @exception -
	 * @comment
	 *  Path나 query에 해당하는 문자열들을 추가해서 원하는 URI를 생성
	 */
	public String makeQuery(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().queryParam("page", page).build();

		return uriComponents.toUriString();
	}

	/**
	 * 크롤링용 파라미터 메서드
	 * @param page
	 * @return String "&date=" + strToday + "&page=" + page
	 * @exception -
	 * @comment
	 *  날짜와 페이지 단위로 특정 뉴스기사를 추출할 파라미터
	 */
	public static String getParameter(int page) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());

		return "&date=" + strToday + "&page=" + page;
	}

	/**
	 * 크롤링용 총 페이지 추출
	 * @param -
	 * @return int maxPage
	 * @exception -
	 * @comment
	 *  뉴스 기사의 총 페이지를 확인
	 */
	public static int maxPage() {
		int idx = 0;
		int i = 1;
		int j = 0;
		int maxPage = 0;

		while (true) {
			Document docPage;
			try {
				docPage = Jsoup.connect(new_URL + getParameter(i)).get();

				Elements elePages = docPage.select(".content .paging strong");

				for (Element elePage : elePages) {
					maxPage = Integer.parseInt(elePage.text());
					if (maxPage == idx) {
						j = 1;
						break;
					}
					idx = maxPage;
				}
				i++;
				if (j == 1) {
					break;
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return maxPage;
	}
}
