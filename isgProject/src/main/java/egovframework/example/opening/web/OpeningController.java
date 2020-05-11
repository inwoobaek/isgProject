package egovframework.example.opening.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpeningController {

	@RequestMapping(value = "/opening.do")
	public String opening(ModelMap model) throws Exception {
		return "opening";
	}
}
