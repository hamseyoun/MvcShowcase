package org.springframework.samples.mvc.redirect;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.test.web.servlet.MockMvc;

public class RedirectControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() throws Exception {
		this.mockMvc = standaloneSetup(new RedirectController(new DefaultFormattingConversionService()))
				.alwaysExpect(status().isFound()).build();
	}

	@Test
	public void uriTemplate() throws Exception {
		this.mockMvc.perform(get("/redirect/uriTemplate"))	//get방식으로 /redirect/uriTemplate이라는 uri를 실행시킴
		.andDo(print())
		.andExpect(redirectedUrl("/redirect/a123"))		;	//만일 addFlashAttribute로 파라미터를 전송했으면 에러가 안뜰것
	}


	@Test
	public void uriComponentsBuilder() throws Exception {
		this.mockMvc.perform(get("/redirect/uriComponentsBuilder"))
		.andDo(print())
		.andExpect(redirectedUrl("/redirect/a123?date=12/31/11"));
	}

}
