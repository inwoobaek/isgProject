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

	private static String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm&date=";

	public static List<SuhoVO> getSuhoVO(int page) throws IOException {

		List<SuhoVO> suhoVOList = new ArrayList<>();
		int idx = (page-1)*20;

		String url2 = null;

		
			
			Document doc = Jsoup.connect(url + getUrl(page)).get();
			
			Elements contents = doc.select(".container #main_content.content ul li dl");

			for (Element content : contents) {
				SuhoVO suhoVO = new SuhoVO();
				
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
				
				Elements href = content.select("a");
				url2 = href.attr("href");
				suhoVO.setHref(url2);
				
				
				
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

		List<SuhoVO> excelVOList = new ArrayList<>();
		
		int idx = 0;
		int pages;
		// int j = 0;
		// int maxPage = 0;
		String url2 = null;

		for (pages = 1; pages <= 5; pages++) {
			
			
			Document doc = Jsoup.connect(url + getUrl(pages)).get();
			
			Elements contents = doc.select(".container #main_content.content ul li dl");

			for (Element content : contents) {
				SuhoVO suhoVO = new SuhoVO();
				
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
				
				Elements href = content.select("a");
				url2 = href.attr("href");
				suhoVO.setHref(url2);
				
				Elements newdate = content.select("dd .date.is_new");
				if(newdate.text().length() > 2) {
				suhoVO.setNewdate(newdate.text());
				}
				
				Elements outdate = content.select("dd .date.is_outdated");
				suhoVO.setOutdate(outdate.text());

				//페이지 넘버
				suhoVO.setPage(pages);
				excelVOList.add(suhoVO);
			}
		}
		
		return excelVOList;
	}

	// 페이지, 날짜 파라미터
	public static String getUrl(int PAGE) {
		Date d = new Date();
		SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");

		return day.format(d) + "&page=" + PAGE;
	}

}
