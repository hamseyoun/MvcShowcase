package org.springframework.samples.mvc.redirect;

import javax.inject.Inject;

import org.joda.time.LocalDate;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;

@Controller
@RequestMapping("/redirect")
public class RedirectController {

	private final ConversionService conversionService;

	@Inject
	public RedirectController(ConversionService conversionService) {
		this.conversionService = conversionService;
	}	//타입을 변환시켜주는 인터페이스 ConversionService

	/*
	redirect의 작동 방식과 특징!
	 클라이언트에서 서버로 요청이 들어오면, 서버는 클라이언트에게 응답코드 302와 함께
	 어디로 가라고 location에 url을 정하여 응답한다.
	 즉 이동을 2번 하게 되는데, model객체에 데이터를 담아서 넘겨도 재이동을 하기 때문에 데이터가 리셋된다.
	*/
	@GetMapping("/uriTemplate")
	public String uriTemplate(RedirectAttributes redirectAttrs) {
		//RedirectAttributes 는 리다이렉트가 발생하기 직전에 모든 속성을 세션에 저장해준다.
		redirectAttrs.addAttribute("account", "a123");  // Used as URI template variable
		redirectAttrs.addAttribute("date", new  LocalDate(2021,02,15));  // Appended as a query parameter --> 이 경우에는 쿼리스트링으로 데이터가 보여지기 때문에 보안에 취약하다.
//		redirectAttrs.addFlashAttribute("date", new  LocalDate(2021,02,15));  // Appended as a query parameter	//파라미터를 숨겨서 보내고 싶으면 addFlashAttribute를 사용하자
		return "redirect:/redirect/{account}";
	}

	@GetMapping("/uriComponentsBuilder")
	public String uriComponentsBuilder() {
		String date = this.conversionService.convert(new LocalDate(2011, 12, 31), String.class);
		//"12/31/11"
		System.out.println(date);
		System.out.println(new LocalDate(2011, 12, 31).toString());
		System.out.println("====");
		/*
		- UriComponents는 uri를 동적으로 생성해주는 클래스이다.
		- UriComponentsBuilder클래스는 여러개의 파라미터를 연결하여 uri로 만들어주는 기능을 한다.
		  queryParam()을 이용해서 파라미터를 연결하고,
		  expand()를 이용해서 {account} 템플릿 변수에 값을 expand해준다.
		  이렇게 엮은 String을 uri로 build()해주는것이다.
		*/
		UriComponents redirectUri = UriComponentsBuilder.fromPath("/redirect/{account}").queryParam("date", date)
				.build().expand("a123").encode();

		System.out.println("redirect:" + redirectUri.toUriString());
		return "redirect:" + redirectUri.toUriString();
	}

	@GetMapping("/{account}")
	public String show(@PathVariable String account, @RequestParam(required=false) LocalDate date) {
		System.out.println(account);
		return "redirect/redirectResults";
	}

}
