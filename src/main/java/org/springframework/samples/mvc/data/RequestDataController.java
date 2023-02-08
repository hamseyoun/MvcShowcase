package org.springframework.samples.mvc.data;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class RequestDataController {

	@GetMapping("param")
	public String withParam(@RequestParam(name="foo") String foo1) {
		return "Obtained 'foo' query parameter value '" + foo1 + "'";
	}

	@GetMapping("group")
	public String withParamGroup(@ModelAttribute JavaBean bean) {
		System.out.println(bean.getParam1() +','+ bean.getParam2() +','+ bean.getParam3());
		return "Obtained parameter group " + bean;
	}


	@GetMapping("path/{var}")
	public String withPathVariable(@PathVariable String var) {
		return "Obtained 'var' path variable value '" + var + "'";
	}

	// => /data/{path}/simple
	//"/data/matrixvars;foo=bar/simple")
	// path = matrixvars
	// foo=bar
	//"Obtained matrix variable 'foo=" + bar + "' from path segment '" + matrixvars + "'";
	@GetMapping("{path}/simple")
	public String withMatrixVariable(@PathVariable String path, @MatrixVariable String foo) {
		return "Obtained matrix variable 'foo=" + foo + "' from path segment '" + path + "'";
	}

	@GetMapping("{path1}/{path2}")
	public String withMatrixVariablesMultiple (
			@PathVariable String path1, @MatrixVariable(name="foo", pathVar="path1") String foo1,
			@PathVariable String path2, @MatrixVariable(name="foo", pathVar="path2") String foo2) {

		return "Obtained matrix variable foo=" + foo1 + " from path segment '" + path1
				+ "' and variable 'foo=" + foo2 + " from path segment '" + path2 + "'";
	}

	@GetMapping("header")
	public String withHeader(@RequestHeader String Accept) {
		return "Obtained 'Accept' header '" + Accept + "'";
	}

	@GetMapping("cookie")
	public String withCookie(@CookieValue String openid_provider) {

		return "Obtained 'openid_provider' cookie '" + openid_provider + "'";
	}

	@PostMapping("body")
	public String withBody(@RequestBody String body) {
		return "Posted request body '" + body + "'";
	}

	@PostMapping("entity")
	public String withEntity(HttpEntity<String> entity) {
		String s = "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
		//System.out.println(entity.getBody());
	//	System.out.println(entity.getHeaders());
		//return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
		return s;
	}

	//Posted request body 'foo'; headers = [Content-Type:"text/plain", Content-Length:"3"]
	//  Posted request body 'foo'; headers = [Content-Type:"text/plain", Content-Length:"3"]
	  //Posted request body 'foo'; headers = {Content-Type="text/plain", Content-Length=3}
}
