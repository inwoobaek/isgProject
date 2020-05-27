package egovframework.example.suho.web;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.suho.service.Criteria;
import egovframework.example.suho.service.ExcelTest;
import egovframework.example.suho.service.NewsCrawler;
import egovframework.example.suho.service.Pagination;
import egovframework.example.suho.service.SuhoVO;

@Controller
public class SuhoController {

	@RequestMapping(value = "/suhoList.do")
	public String list(ModelMap model, @RequestParam(value="page", defaultValue="1")
	int page, Criteria cri) throws Exception {
		
		/* 기사를 담을 SuVO List를 생성한 뒤 
		 * getSuhoVO 메소드를 이용해 리스트에 추가
		 */
		List<SuhoVO> newsList = NewsCrawler.getSuhoVO(page);
		
		// 리스트 객체를 jsp로 넘겨줍니다.
		model.addAttribute("newsList", newsList);
		
		// 페이징 처리를 위한 pagination 객체 생성
		Pagination pagination = new Pagination();
		
		// 초기값 셋팅을 위해 cri 파라미터를 이용
		pagination.setCri(cri);
		
		//총 페이지 숫자를 구하기 위해 필요한 총 게시물 갯수
		pagination.setTotalCount(pagination.getMaxPage()*20);
		
		// 생성된 pagination 객체를 jsp로 넘겨줍니다.
		model.addAttribute("pagination", pagination);
		
		return "suho/suhoList";
	}
	
	// 등록 기능 구현중 (미구현입니다.)
	@RequestMapping(value = "/suhoMgmt.do")
	public String mgmt(ModelMap model) throws Exception {
		return "suho/suhoMgmt";
	}
	// 상세보기 기능 (미구현입니다.)
	@RequestMapping(value = "/suhoView.do")
	public String view(ModelMap model) throws Exception {
		return "suho/suhoView";
	}
	
	// 엑셀 다운로드(최근 100개 기사)
	@RequestMapping(value = "/suhoExcel.do")
	public String excel(ModelMap model) throws Exception {
		
		List<SuhoVO> excelList = NewsCrawler.getExcelVO();
		model.addAttribute("excelList", excelList);
		
		return "suho/suhoExcel";
	}
	
	// 엑셀 다운로드(현재 페이지의 20개 기사)
	@RequestMapping(value = "/test.do")
	public String test(ModelMap model, @RequestParam(value="page", defaultValue="1")
	int page, Criteria cri) throws Exception {
		List<SuhoVO> excelList = NewsCrawler.getExcelVO();
		ExcelTest.ExcelWrite(excelList);
		
		List<SuhoVO> newsList = NewsCrawler.getSuhoVO(page);
		model.addAttribute("newsList", newsList);
		
		Pagination pagination = new Pagination();
		pagination.setCri(cri);
		pagination.setTotalCount(pagination.getMaxPage()*20);
		model.addAttribute("pagination", pagination);
		
		return "suho/suhoList";
	}
}
