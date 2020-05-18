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
	// 1. URL
	private static String url = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm&date=";

	public static List<SuhoVO> getSuhoVO() throws IOException {

		List<SuhoVO> suhoVOList = new ArrayList<>();
		int idx = 0;
		int pages = 1;
		int j = 0;
		int maxPage = 0;

		while (true) {
			try {
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		
		idx = 0;
		for (pages = 1; pages <= maxPage; pages++) {
			System.out.println("==========[" + pages + "] 페이지==========\n");

			// 2. HTML 가져오기(페이지 단위)
			Document doc = Jsoup.connect(url + getUrl(pages)).get();

			// 3. Element
			Elements contents = doc.select(".container #main_content.content ul li dl");

			for (Element content : contents) {
				SuhoVO suhoVO = new SuhoVO();

				
				idx++;
				suhoVO.setIdx(idx);

				
				Elements title = content.select("dt a");
				suhoVO.setTitle(title.text());

				
				Elements ddcontents = content.select("dd .lede");
				suhoVO.setView(ddcontents.text());

				
				Elements writer = content.select("dd .writing");
				suhoVO.setWriter(writer.text());

				
				suhoVO.setPage(pages);

				suhoVOList.add(suhoVO);
			}
		}
		return suhoVOList;
	}

	// 페이지, 날짜 파라미터
	public static String getUrl(int PAGE) {
		Date d = new Date();

		SimpleDateFormat day = new SimpleDateFormat("yyyyMMdd");
		// Calendar c1 = Calendar.getInstance();
		// String strToday = day.format(c1.getTime());

		return day.format(d) + "&page=" + PAGE;
	}

}
