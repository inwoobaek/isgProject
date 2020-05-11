package egovframework.example.suho.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuhoController {

	@RequestMapping(value = "/suhoTest.do")
	public String suhoTest(ModelMap model) throws Exception {
		return "suho/suhoTest";
	}
}
