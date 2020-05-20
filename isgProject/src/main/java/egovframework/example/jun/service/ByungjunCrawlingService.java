package egovframework.example.jun.service;

import java.io.*;
import java.util.*;

import java.text.SimpleDateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import egovframework.example.jun.service.ByungjunVO;

@Service
public class ByungjunCrawlingService extends Paging{
	// 1. URL
	private static String new_URL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm";

	public static List<ByungjunVO> getCrawling(int page) throws IOException {
		
		List<ByungjunVO> byungjunVOList = new ArrayList<>();

		/** 네이버 뉴스 경제일반 크롤링 */
		int idx = (page-1)*20;

		// 2. HTML 가져오기(페이지 단위)
		Document doc = Jsoup.connect(new_URL + getParameter(page)).get();

		// 3. Element
		Elements contents = doc.select(".container #main_content.content ul li dl");

			for (Element content : contents) {
				ByungjunVO byungjunVO = new ByungjunVO();
				
				/** ID */
				idx++;
				byungjunVO.setIdx(idx);
				
				/** 제목 */
				Elements title = content.select("dt a");
				byungjunVO.setTitle(title.text());
	
				/** 내용 */
				Elements ddcontents = content.select("dd .lede");
				byungjunVO.setContents(ddcontents.text());

				/** 작성자 */
				Elements writer = content.select("dd .writing");
				byungjunVO.setWriter(writer.text());
				
				/** 페이지 */
				byungjunVO.setPage(page);
				
				byungjunVOList.add(byungjunVO);
			}
		
		return byungjunVOList;
	}

}

