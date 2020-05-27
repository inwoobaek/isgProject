package egovframework.example.suho.service;

import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

@Service
public class NewsCrawler {

	//네이버 경제 뉴스 base url
	private static String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm&date=";

	public static List<SuhoVO> getSuhoVO(int page) throws IOException {

		List<SuhoVO> suhoVOList = new ArrayList<>();
		int idx = (page-1)*20; //idx는 1~20, 21~40 ...

		// 기사 링크를 담을 변수
		String url2 = null;

		
			//get()메소드를 통해 Document타입의 변수 doc에 대입
			Document doc = Jsoup.connect(url + getUrl(page)).get();
			
			//select를 이용하여 원하는 태그를 선택한다.
			Elements contents = doc.select(".container #main_content.content ul li dl");

			for (Element content : contents) {
				SuhoVO suhoVO = new SuhoVO();
				
				// 번호
				idx++;
				suhoVO.setIdx(idx);

				// 뉴스 제목
				Elements title = content.select("dt a");
				suhoVO.setTitle(title.text());

				// 내용 미리보기
				Elements view = content.select("dd .lede");
				suhoVO.setView(view.text());

				// 작성자
				Elements writer = content.select("dd .writing");
				suhoVO.setWriter(writer.text());
				
				// 하이퍼링크
				Elements href = content.select("a");
				url2 = href.attr("href");
				suhoVO.setHref(url2);
				
				
				/* 등록시간
				 * 최근 등록시간이 1시간 이내일 경우 date.is_new
				 * 1시간 이후일 경우 date.is_outdated
				 * */
				Elements newdate = content.select("dd .date.is_new");
				if(newdate.text().length() > 2) {
				suhoVO.setNewdate(newdate.text());
				}
				
				Elements outdate = content.select("dd .date.is_outdated");
				suhoVO.setOutdate(outdate.text());

				//페이지 넘버
				suhoVO.setPage(page);
				suhoVOList.add(suhoVO);
			}
			
		return suhoVOList;
	}
	
	public static List<SuhoVO> getExcelVO() throws IOException {

		
		/* 상단의 getSuhoVO() 메소드와 동일한 기능
		 * 엑셀 다운로드를 위해5페이지(100개)의 기사를 크롤링
		 */
		
		List<SuhoVO> excelVOList = new ArrayList<>();
		
		int idx = 0;
		int page;
		
		// 기사 링크를 담을 변수
		String url2 = null;

		for (page = 1; page <= 5; page++) {
			
			
			Document doc = Jsoup.connect(url + getUrl(page)).get();
			
			Elements contents = doc.select(".container #main_content.content ul li dl");

			
			for (Element content : contents) {
				SuhoVO suhoVO = new SuhoVO();
				
				// 번호
				idx++;
				suhoVO.setIdx(idx);

				// 뉴스 제목
				Elements title = content.select("dt a");
				suhoVO.setTitle(title.text());

				// 내용 미리보기
				Elements view = content.select("dd .lede");
				suhoVO.setView(view.text());

				// 작성자
				Elements writer = content.select("dd .writing");
				suhoVO.setWriter(writer.text());
				
				// 하이퍼링크
				Elements href = content.select("a");
				url2 = href.attr("href");
				suhoVO.setHref(url2);

				/* 등록시간
				 * 최근 등록시간이 1시간 이내일 경우 date.is_new
				 * 1시간 이후일 경우 date.is_outdated
				 * */
				
				Elements newdate = content.select("dd .date.is_new");
				if(newdate.text().length() > 2) {
				suhoVO.setNewdate(newdate.text());
				}
				
				Elements outdate = content.select("dd .date.is_outdated");
				suhoVO.setOutdate(outdate.text());

				//페이지 넘버
				suhoVO.setPage(page);
				excelVOList.add(suhoVO);
			}
		}
		
		return excelVOList;
	}

	// base url에서 날짜 파라미터와 페이지넘버 파라미터를 설정하여 리턴해주는 메소드
	public static String getUrl(int PAGE) {
		Date d = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");

		return day.format(d) + "&page=" + PAGE;
	}

}
