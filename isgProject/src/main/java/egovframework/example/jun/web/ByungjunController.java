package egovframework.example.jun.web;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.example.jun.service.ByungjunCrawlingService;
import egovframework.example.jun.service.ByungjunVO;
import egovframework.example.jun.service.WriteListToExcelFile;

@Controller
public class ByungjunController {
	
		@RequestMapping(value = "/junList.do", method=RequestMethod.GET)
		public String junList(ModelMap model) throws Exception {
	        
	        /** 크롤링 데이터 */
			List<ByungjunVO> byungjunVOList = ByungjunCrawlingService.getByungjunVO();
			model.addAttribute("NaverEconomy", byungjunVOList);
			
			WriteListToExcelFile.writeListToFile("naver.xlsx", byungjunVOList);
			
			return "byungjun/junList";
		}
		
		@RequestMapping(value = "/junMgmt.do")
		public String mgmt(ModelMap model) throws Exception 
		{
			return "byungjun/junMgmt";
		}
		
		@RequestMapping(value = "/junView.do")
		public String view(ModelMap model) throws Exception 
		{
			return "byungjun/junView";
		}
}
