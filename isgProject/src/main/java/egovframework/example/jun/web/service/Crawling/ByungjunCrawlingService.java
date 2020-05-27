package egovframework.example.jun.web.service.Crawling;

import java.io.*;
import java.util.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import egovframework.example.jun.web.service.ByungjunVO;
import egovframework.example.jun.web.service.Paging.Paging;

/**
 * @Class Name : ByungjunCrawlingService.java
 * @Description : ByungjunCrawlingService Class
 * @Modification Information
 * @
 * @  수정일                    수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.05.27     이병준            코멘트 작성
 *
 * @author 인실리코젠 BS팀
 * @since 2020.05.27
 */

@Service
public class ByungjunCrawlingService extends Paging {

	private static String new_URL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2=263&sid1=101&mid=shm";

	/**
	 * 페이징을 위한 크롤링 메서드 ( page=1> 1페이지의 20개 기사 크롤링 )
	 * 
	 * @param page
	 * @return List<ByungjunVO>
	 * @exception IOException
	 * @comment 해당 페이지는 Controller와 연동되어 있으며 페이지에 따른 기사를 크롤링한다.
	 */
	public static List<ByungjunVO> getPageCrawling(int page) throws IOException {

		List<ByungjunVO> byungjunVOList = new ArrayList<>();
		int idx = (page - 1) * 20; // 번호
		Elements title; // 제목
		Elements ele_contents; // 내용
		Elements writer; // 작성자
		Elements href; // 링크

		// 크롤링
		Document doc = Jsoup.connect(new_URL + getParameter(page)).get();
		Elements contents = doc.select(".container #main_content.content ul li dl");
		for (Element content : contents) {
			ByungjunVO byungjunVO = new ByungjunVO();

			/** ID */
			idx++;
			byungjunVO.setIdx(idx);

			/** 제목 */
			title = content.select("dt a");
			byungjunVO.setTitle(title.text());

			/** 내용 */
			ele_contents = content.select("dd .lede");
			byungjunVO.setContents(ele_contents.text());

			/** 작성자 */
			writer = content.select("dd .writing");
			byungjunVO.setWriter(writer.text());

			/** 페이지 */
			byungjunVO.setPage(page);

			/** 링크 */
			href = content.select("a");
			String urlHref = href.attr("href");
			byungjunVO.setHref(urlHref);

			byungjunVOList.add(byungjunVO);
		}

		return byungjunVOList;
	}

	/**
	 * Excel을 위한 크롤링 메서드 ( page=0~4 : 총 100개 기사 크롤링 )
	 * 
	 * @param -
	 * @return List<ByungjunVO>
	 * @exception IOException
	 * @comment 해당 페이지는 Controller와 연동되어 있지 않으며 발표를 위해 준비한 엑셀의 크롤링 함수이다.
	 */
	public static List<ByungjunVO> getExcelCrawling() throws IOException {

		List<ByungjunVO> byungjunVOList = new ArrayList<>();
		int idx = 0;
		Elements title;
		Elements ele_contents;
		Elements writer;
		Elements href;

		for (int i = 0; i < 5; i++) {

			Document doc = Jsoup.connect(new_URL + getParameter(i)).get();
			Elements contents = doc.select(".container #main_content.content ul li dl");
			for (Element content : contents) {
				ByungjunVO byungjunVO = new ByungjunVO();

				/** ID */
				idx++;
				byungjunVO.setIdx(idx);

				/** 제목 */
				title = content.select("dt a");
				byungjunVO.setTitle(title.text());

				/** 내용 */
				ele_contents = content.select("dd .lede");
				byungjunVO.setContents(ele_contents.text());

				/** 작성자 */
				writer = content.select("dd .writing");
				byungjunVO.setWriter(writer.text());

				/** 페이지 */
				byungjunVO.setPage(i);

				/** 링크 */
				href = content.select("a");
				String urlHref = href.attr("href");
				byungjunVO.setHref(urlHref);

				byungjunVOList.add(byungjunVO);
			}
		}

		return byungjunVOList;
	}
}
