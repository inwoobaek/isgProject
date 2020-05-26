package egovframework.example.jun.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.jun.service.ByungjunCrawlingService;
import egovframework.example.jun.service.ByungjunVO;
import egovframework.example.jun.service.Criteria;
import egovframework.example.jun.service.Paging;

@Controller
public class ByungjunController extends Paging {

	@RequestMapping(value = "/junList.do", method = RequestMethod.GET)
	public String junList(ModelMap model, @RequestParam(value = "page", defaultValue = "1") int page, Criteria cri)
			throws Exception {
		int total = maxPage() * 20;

		/** 크롤링 데이터 */
		List<ByungjunVO> byungjunVOList = ByungjunCrawlingService.getPageCrawling(page);
		model.addAttribute("NaverEconomy", byungjunVOList);

		/** 페이징 */
		Paging paging = new Paging();
		paging.setCri(cri);
		paging.setTotalCount(total);
		model.addAttribute("paging", paging);

		return "byungjun/junList";
	}
	
	@RequestMapping(value = "/junlistExcel.do")
	public String junlistExcel(ModelMap model) throws Exception {

		/** 크롤링 데이터 */
		List<ByungjunVO> excelList = ByungjunCrawlingService.getExcelCrawling();
		model.addAttribute("NaverEconomy", excelList);

		return "byungjun/junlistExcel";
	}

	/** 등록화면 */
	@RequestMapping(value = "/junMgmt.do")
	public String mgmt(ModelMap model) throws Exception {

		return "byungjun/junMgmt";
	}

	/** 상세화면 */
	@RequestMapping(value = "/junView.do", method = RequestMethod.GET)
	public String view(ModelMap model) throws Exception {
		
		return "byungjun/junView";
	}

}
