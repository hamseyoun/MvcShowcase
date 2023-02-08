package org.springframework.samples.mvc.simple;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.http.MediaType;

public class SimpleControllerRevisitedTests {

	@Test
	public void simple() throws Exception {
		standaloneSetup(new SimpleControllerRevisited()).build()
			.perform(get("/simple/revisited").accept(MediaType.TEXT_PLAIN))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
			.andExpect(content().string("Hello world revisited!"));
	}

}
