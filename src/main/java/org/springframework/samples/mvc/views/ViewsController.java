package org.springframework.samples.mvc.views;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/*")
public class ViewsController {

	@GetMapping("html")
	public String prepare(Model model) {
		System.out.println(model);
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
		System.out.println(model);
		return "views/html";
	}

	@GetMapping("/viewName")
	public void usingRequestToViewNameTranslator(Model model) {
		model.addAttribute("foo", "bar");
		model.addAttribute("fruit", "apple");
	}
	//"/views/   pathVariables/bar/apple"
	@GetMapping("pathVariables/{foo}/{fruit}")
	public String pathVars(Model model, @PathVariable String foo, @PathVariable String fruit) {
		// No need to add @PathVariables "foo" and "fruit" to the model
		// They will be merged in the model before rendering
		model.addAttribute("foo", foo);
		model.addAttribute("fruit", fruit);
		return "views/html";
	}

	@GetMapping("dataBinding/{foo}/{fruit}")
	public String dataBinding(@Valid JavaBean javaBean) {
		// JavaBean "foo" and "fruit" properties populated from URI variables
//		System.out.println(model);
		return "views/dataBinding";
	}

}
