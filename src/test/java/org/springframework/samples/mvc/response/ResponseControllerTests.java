package org.springframework.samples.mvc.response;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

public class ResponseControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(new ResponseController()).build();
	}

	@Ignore
	@Test
	public void responseBody() throws Exception {
		this.mockMvc.perform(get("/response/annotation"))
		.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("The String ResponseBody"));
	}

	@Ignore
	@Test
	public void responseCharsetAccept() throws Exception {
		this.mockMvc.perform(
				get("/response/charset/accept")
					.accept(new MediaType("text", "plain", Charset.forName("UTF-8"))))
//				.accept(new MediaType("text", "plain")))
				.andExpect(status().isOk())
			//	.andExpect(content().string(
			//			"\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)"))
				.andDo(print());
	}

	@Ignore
	@Test
	public void responseCharsetProduce() throws Exception {
		this.mockMvc.perform(get("/response/charset/produce"))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().string(
						"\u3053\u3093\u306b\u3061\u306f\u4e16\u754c\uff01 (\"Hello world!\" in Japanese)"));
	}

	@Ignore
	@Test
	public void responseEntityStatus() throws Exception {
		this.mockMvc.perform(get("/response/entity/status"))
				.andExpect(status().isForbidden())
				.andExpect(content().string(
						"The String ResponseBody with custom status code (403 Forbidden)"))
				.andDo(print());
	}

//	@Ignore
	@Test
	public void responseEntityHeaders() throws Exception {
		this.mockMvc.perform(get("/response/entity/headers"))
		.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(
						"The String ResponseBody with custom header Content-Type=text/plain"));
	}

}
