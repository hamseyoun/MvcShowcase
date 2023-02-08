package org.springframework.samples.mvc.data.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomArgumentController {

	@ModelAttribute
	void beforeInvokingHandlerMethod(HttpServletRequest request) {
		System.out.println("#beforeInvokingHandlerMethod");
		request.setAttribute("foo", "bar");
	}

	@GetMapping("/data/custom")
	public String custom(@RequestAttribute("foo") String foo) {
		System.out.println("#custom");
		return "Got 'foo' request attribute value '" + foo + "'";
	}

}
