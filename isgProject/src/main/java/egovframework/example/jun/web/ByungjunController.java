package egovframework.example.jun.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.jun.web.service.ByungjunVO;
import egovframework.example.jun.web.service.Crawling.ByungjunCrawlingService;
import egovframework.example.jun.web.service.Paging.Criteria;
import egovframework.example.jun.web.service.Paging.Paging;

/**
 * @Class Name : ByungjunController.java
 * @Description : ByungjunController Class
 * @Modification Information
 * @
 * @  수정일                    수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2020.05.27     이병준            코멘트 작성
 *
 * @author 인실리코젠 BS팀
 * @since 2020.05.27
 */

@Controller
public class ByungjunController extends Paging {

	/**
	 * 게시판 페이지
	 * 
	 * @param model, page, cri
	 * @return "byungjun/junList"
	 * @exception -
	 * @comment model: JSP에서 사용하기 위해 이용 
	 * 			page : 크롤링을 위해 사용되며, 맨 처음 페이지 값은 1로 유지한다.
	 *          cri : 페이지의 기준 데이터
	 */
	@RequestMapping(value = "/junList.do", method = RequestMethod.GET)
	public String junList(ModelMap model, @RequestParam(value = "page", defaultValue = "1") int page, Criteria cri)
			throws Exception {

		/** 총 페이지 */
		int total = maxPage() * 20;

		/** 페이지 크롤링 */
		List<ByungjunVO> byungjunVOList = ByungjunCrawlingService.getPageCrawling(page);
		model.addAttribute("NaverEconomy", byungjunVOList);

		/** 페이징 */
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(total);
		model.addAttribute("paging", paging);

		return "byungjun/junList";
	}

	/**
	 * 엑셀 다운로드 페이지
	 * 
	 * @param model
	 * @return "byungjun/junlistExcel"
	 * @exception -
	 * @comment 엑셀 크롤링으로 최신 기사 100개가 테이블화 되어있다.
	 */
	@RequestMapping(value = "/junlistExcel.do")
	public String junlistExcel(ModelMap model) throws Exception {

		/** 엑셀크롤링 */
		List<ByungjunVO> excelList = ByungjunCrawlingService.getExcelCrawling();
		model.addAttribute("NaverEconomy", excelList);

		return "byungjun/junlistExcel";
	}

	/**
	 * 등록 페이지
	 * 
	 * @param model
	 * @return "byungjun/junMgmt"
	 * @exception -
	 * @comment 미완성
	 */
	@RequestMapping(value = "/junMgmt.do")
	public String mgmt(ModelMap model) throws Exception {

		return "byungjun/junMgmt";
	}

	/**
	 * 상세화면 페이지
	 * 
	 * @param model
	 * @return "byungjun/junView"
	 * @exception -
	 * @comment 미완성
	 */
	@RequestMapping(value = "/junView.do", method = RequestMethod.GET)
	public String view(ModelMap model) throws Exception {

		return "byungjun/junView";
	}

}
