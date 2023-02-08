package org.springframework.samples.mvc.validation;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

public class ValidationControllerTests {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = standaloneSetup(new ValidationController()).alwaysExpect(status().isOk()).build();
	}

	@Test
	public void validateSuccess() throws Exception {
		this.mockMvc.perform(get("/validate?number=10&date=2029-07-04"))
				.andDo(print())
				.andExpect(content().string("No errors"));
	}

	@Test
	public void validateErrors() throws Exception {
		this.mockMvc.perform(get("/validate?number=3&date=2010-07-01"))
				.andExpect(content().string("Object has validation errors"))
				.andDo(print());
	}

}
