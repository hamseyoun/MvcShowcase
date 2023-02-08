package org.springframework.samples.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MappingController {

	@GetMapping("/mapping/path")
	public String byPath() {
		System.out.println("/mapping/path");
		return "Mapped by path!";
	}
	///mapping/path/wildcard
	@GetMapping("/mapping/path/*")
	public String byPathPattern(HttpServletRequest request) {
		return "Mapped by path pattern ('" + request.getRequestURI() + "')";
	}

	@GetMapping("/mapping/method")
	public String byMethod() {
		return "Mapped by path + method";
	}

	@GetMapping(path="/mapping/parameter", params="foo")	//params 옵션은 이 params와 일치하는 파라미터가 오면 이 핸들러로 매핑해준다.
	public String byParameter() {
		return "Mapped by path + method + presence of query parameter!";
	}

	@GetMapping(path="/mapping/parameter", params="!foo")
	public String byParameterNegation() {
		return "Mapped by path + method + not presence of query parameter!";
	}

	@GetMapping(path="/mapping/header", headers="FooHeader=foo")	//header 옵션을 사용해서 들어오는 header가 Fooheader=foo 면 이 핸들러로 매핑해준다.
	public String byHeader() {
		return "Mapped by path + method + presence of header!";
	}

	@GetMapping(path="/mapping/header", headers="!FooHeader")
	public String byHeaderNegation() {
		return "Mapped by path + method + absence of header!";
	}

	@PostMapping(path="/mapping/consumes", consumes=MediaType.APPLICATION_JSON_VALUE)	//요청데이터가 JSON인 요청만 받을것이라고 명시
	public String byConsumes(@RequestBody JavaBean javaBean) {	//클라이언트가 전송하는 JSON객체를 java객체로 변환시켜서 값을 바인딩할 수 있게 해준다.
		System.out.println("JavaBean = " + javaBean);
		return "Mapped by path + method + consumable media type (javaBean '" + javaBean + "')";
	}

	@GetMapping(path="/mapping/produces", produces=MediaType.APPLICATION_JSON_VALUE)	//produces는 response의 accept-request header가 특정 미디어타입으로 지정되도록 하는 옵션
	public JavaBean byProducesJson() {													//@RestController에서는 기본으로 반환값이 JSON으로 지정되어있기 때문에 produces=MediaType.APPLICATION_JSON_VALUE 를 생략해도 무방하다.
		System.out.println("json controller method");
		return new JavaBean("bar","apple");
	}
	//get("/mapping/produces").accept(MediaType.APPLICATION_XML
	@GetMapping(path="/mapping/produces", produces=MediaType.APPLICATION_XML_VALUE)
	public JavaBean byProducesXml() {
		System.out.println("Xml controller method");
		return new JavaBean("bar","apple");
	}

}
