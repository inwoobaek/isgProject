package egovframework.example.jun.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ByungjunController {
	
	@RequestMapping(value = "/junList.do")
	public String junList(ModelMap model) throws Exception {
		return "byungjun/junList";
	}
}