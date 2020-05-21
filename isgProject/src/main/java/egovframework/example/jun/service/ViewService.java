package egovframework.example.jun.service;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class ViewService {

	/** URL */
	public String viewQuery(int idx) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/isgProject/junView.do").queryParam("idx", idx).build();

		return uriComponents.toUriString();
	}
	
}
