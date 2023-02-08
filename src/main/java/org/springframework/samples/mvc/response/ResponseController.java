package org.springframework.samples.mvc.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/response", method=RequestMethod.GET)
public class ResponseController {

	@GetMapping("/annotation")
	public String responseBody() {
		return "The String ResponseBody";
	}

	@GetMapping("/charset/accept")
	public String responseAcceptHeaderCharset() {
//		return "\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)";
		return "\uC548\uB155\uD558\uC138\uC694 (\"Hello world!\" in Korea)";
		//https://huskdoll.tistory.com/39
//		return "안녕하세요";
	}

	@GetMapping(value="/charset/produce", produces="text/plain;charset=UTF-8")
	public String responseProducesConditionCharset() {
		return "\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)";
//return "\uC548\uB155\uD558\uC138\uC694 (\"Hello world!\" in Korea)";
	}

	@GetMapping("/entity/status")
	public ResponseEntity<String> responseEntityStatusCode() {
		return new ResponseEntity<String>("The String ResponseBody with custom status code (403 Forbidden)",
				HttpStatus.FORBIDDEN);
	}

//	@GetMapping("/entity/status")
//	public String responseEntityStatusCode() {
//		return"The String ResponseBody with custom status code (403 Forbidden)"
//				;
//	}

	@GetMapping("/entity/headers")
	public ResponseEntity<String> responseEntityCustomHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);

		return new ResponseEntity<String>("The String ResponseBody with custom header Content-Type=text/plain",
				headers, HttpStatus.OK);
	}

}
