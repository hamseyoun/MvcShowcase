package org.springframework.samples.mvc.validation;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController {

	// enforcement of constraints on the JavaBean arg require a JSR-303 provider on the classpath
	// JavaBean의 필드들에 대한 제약조건을 적용하려면 classpath에 JSR-303 공급자가 필요하다.
	// Bean Validation 1.0 은 JSR-303 이고
	// Bean Validation 2.0 은 JSR-380 이다.

	//@Valid는 JSR-303 표준스펙으로써 빈검증기(Bean Validator)를 이용해 객체의 제약조건을 지시하는 어노테이션이다.

	//동작과정
	/*
	요청이 DispatcherServler(FrontController)로 들어옴
	RequestMappinghandlerMapping 에서 handler 조회
	RequestMappingHandlerAdapter 가 handler 를 호출하기 전에 ArgumentResolver를 호출
	ArgumentResolver는 컨트롤러의 파라미터/어노테이션 정보를 기반으로 전달할 데이터를 생성하여
	이를 파라미터에 담아서 컨트롤러(handler)에 전달한다.
	이 @Valid 역시 ArgumentResolver에 의해 처리가 된다.
	*/

	//BindingResult는 검증오류가 발생할 경우 오류내용을 보관하는 스프링프레임워크에서 제공하는 객체이다.
	@GetMapping("/validate")
	public String validate(@Valid JavaBean bean, BindingResult result) {
		System.out.println("result =============================== " + result);

		if (result.hasErrors()) {
			return "Object has validation errors";
		} else {
			return "No errors";
		}
	}

}
