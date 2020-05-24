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
	public String suhoTest(ModelMap model, @RequestParam(value="page", defaultValue="1") int page, Criteria cri) throws Exception {
		
		List<SuhoVO> newsList = NewsCrawler.getSuhoVO(page);
		model.addAttribute("newsList", newsList);
		
		Pagination pagination = new Pagination();
		pagination.setCri(cri);
		pagination.setTotalCount(pagination.getMaxPage()*20);
		model.addAttribute("pagination", pagination);
		
		
		
		return "suho/suhoList";
	}
	
	@RequestMapping(value = "/list.do")
	public String list(ModelMap model) throws Exception {
		return "suho/list";
	}
	
	@RequestMapping(value = "/suhoMgmt.do")
	public String mgmt(ModelMap model) throws Exception {
		return "suho/suhoMgmt";
	}
	
	@RequestMapping(value = "/suhoView.do")
	public String view(ModelMap model) throws Exception {
		return "suho/suhoView";
	}
	
	@RequestMapping(value = "/suhoExcel.do")
	public String excel(ModelMap model) throws Exception {
		
		List<SuhoVO> excelList = NewsCrawler.getExcelVO();
		model.addAttribute("excelList", excelList);
		
		return "suho/suhoExcel";
	}
	
}
