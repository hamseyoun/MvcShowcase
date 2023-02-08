package org.springframework.samples.mvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
시작하기 전에 알아두기 - Exception 처리 과정!
DispatcherServlet ->
HandlerMapping(RequestMappingHandlerMapping) ->
HandlerAdapter(RequestMappingHandlerAdapter) ->
Interceptor-prehandler ->
Handler (예외 발생!) ->
HandlerExceptionResolver 가 에외를 받아서 처리하기 때문에

Interceptor-posthandler까지 오지 않음
*/
@RestController
public class ExceptionController {

	@GetMapping("/exception")
	public String exception() {
		throw new IllegalStateException("Sorry!");	//객체 상태가 메서드 호출을 처리하기에 적절치 않을때
	}

	@GetMapping("/global-exception")
	public String businessException() throws BusinessException {

		throw new BusinessException();
	}


//	@ExceptionHandler 는 Controller 계층에서 발생하는 에러를 잡아서 메서드로 처리해주는 기능이다.
//	Service, Repository 에서 발생하는 에러는 제외한다.
//	@ExceptionHandler 는 예외를 정상처리 시켜주기 때문에 응답코드가 200이다. 응답 에러코드를 반환하기 위해서는 HttpStatus속성을 사용하면 된다
//	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	public String handle(IllegalStateException e) {	//IllegalStateException 에러를 받아서 처리하는 메서드를 작성함.
		System.out.println(e.getMessage());
		return "IllegalStateException handled!";
	}


	/*
	 * @ExceptionHandler public String handleBusinessException(BusinessException ex)
	 * { return "test IllegalStateException handled!"; }
	 */

}
