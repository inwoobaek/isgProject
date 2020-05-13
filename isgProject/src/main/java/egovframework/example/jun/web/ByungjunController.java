package egovframework.example.jun.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.example.jun.service.ByungjunCrawlingService;
import egovframework.example.jun.service.ByungjunVO;

@Controller
public class ByungjunController {
	
	@RequestMapping(value = "/junList.do")
	public String junList(ModelMap model) throws Exception {

		List<ByungjunVO> byungjunVOList = ByungjunCrawlingService.getByungjunVO();
		model.addAttribute("NaverEconomy", byungjunVOList);
		
		/** 작동테스트 : 정상 */
		/*System.out.println(byungjunVOList.toString());*/
			
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
