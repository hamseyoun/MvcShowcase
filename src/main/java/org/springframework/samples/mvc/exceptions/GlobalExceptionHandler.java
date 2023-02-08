package org.springframework.samples.mvc.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdevice 는 마치 AOP처럼
//@ExceptionHandler 등이 적용된 메서드들을 AOP를 적용해
//Controller단에 적용하기 위해 고안된 어노테이션이다.
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public String handleBusinessException(BusinessException ex) {
		System.out.println(ex.getMessage());
		return "Handled BusinessException";
	}

}
