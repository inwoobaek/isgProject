package egovframework.example.jun.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Paging {
	/** URL */
	private static String new_URL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm";

	/** 파라미터 */
	public static String getParameter(int PAGE) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());

		return "&date=" + strToday + "&page=" + PAGE;
	}
	
	/** 총 페이지 */
	public static int maxPage() {
		int idx = 0;
		int i = 1;
		int j = 0;
		int maxPage = 0;
		
		while(true) {
			Document docPage;
			try {
				docPage = Jsoup.connect(new_URL + getParameter(i)).get();
				
				Elements elePages = docPage.select(".content .paging strong");
				
				for(Element elePage : elePages) {
					maxPage = Integer.parseInt(elePage.text());
					if( maxPage == idx) {
						j=1;
						break;
					}
					idx = maxPage;
				}
				i++;
				if( j == 1) {
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
