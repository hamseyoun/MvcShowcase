package org.springframework.samples.mvc.exceptions;

@SuppressWarnings("serial")
public class BusinessException extends Exception {
	public BusinessException() {
		super("global-Exception 발생");
	}
}

