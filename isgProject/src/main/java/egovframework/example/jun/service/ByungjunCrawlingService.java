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
public class ByungjunCrawlingService {
	// 1. URL
	private static String new_URL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm";

	public static List<ByungjunVO> getByungjunVO() throws IOException {
		
		List<ByungjunVO> byungjunVOList = new ArrayList<>();
		int idx = 0;
		
		for (int i = 1; i <= 1; i++) {
			System.out.println("==========[" + i + "] 페이지==========\n");

			// 2. HTML 가져오기(페이지 단위)
			Document doc = Jsoup.connect(new_URL + getParameter(i)).get();

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
				byungjunVO.setPage(i);
				
				byungjunVOList.add(byungjunVO);
			}
		}
		/** 작동테스트 : 정상 */
		/*System.out.println(byungjunVOList.toString());*/
		return byungjunVOList;
	}

	// 페이지, 날짜 파라미터
	public static String getParameter(int PAGE) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c1 = Calendar.getInstance();
		String strToday = sdf.format(c1.getTime());

		return "&date=" + strToday + "&page=" + PAGE;
	}

}

