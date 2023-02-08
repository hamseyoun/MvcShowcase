package org.springframework.samples.mvc.messageconverters;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.samples.mvc.AbstractContextControllerTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageConvertersControllerTests extends AbstractContextControllerTests {

	private static String URI = "/messageconverters/{action}";

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
	}

//	@Ignore
	@Test
	public void readString() throws Exception {
		this.mockMvc.perform(post(URI, "string").characterEncoding("UTF-8").content("foo".getBytes()))
		.andDo(print())
				.andExpect(content().string("Read string 'foo'"));
	}

//	@Ignore
	@Test
	public void writeString() throws Exception {
		this.mockMvc.perform(get(URI, "string"))
		.andDo(print())
			.andExpect(content().string("Wrote a string"));
	}

//	@Ignore
	@Test
	public void readForm() throws Exception {
		this.mockMvc.perform(
				post(URI, "form")
					.contentType(MediaType.APPLICATION_FORM_URLENCODED)	//application/x-www-form-urlencoded는 html 의 form 의 기본 content-type 이다.
					.param("foo", "bar")
					.param("fruit", "apple"))
					.andDo(print())
				.andExpect(content().string("Read x-www-form-urlencoded: JavaBean {foo=[bar], fruit=[apple]}"));
	}

//	@Ignore
	@Test
	public void writeForm() throws Exception {
		this.mockMvc.perform(get(URI, "form"))
		.andDo(print())
//				.andExpect(content().contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andExpect(content().string("foo=bar&fruit=apple"));
	}

	private static String XML =
			"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
			"<javaBean><foo>bar</foo><fruit>apple</fruit></javaBean>";

//	@Ignore
	@Test
	public void readXml() throws Exception {
		this.mockMvc.perform(
				post(URI, "xml")
					.contentType(MediaType.APPLICATION_XML)
						.characterEncoding("UTF-8")
					.content(XML.getBytes()))
		.andDo(print())
				.andExpect(content().string("Read from XML: JavaBean {foo=[bar], fruit=[apple]}"));
	}

//	@Ignore
	@Test
	public void writeXml() throws Exception {
		this.mockMvc.perform(get(URI, "xml").accept(MediaType.APPLICATION_XML))	//MediaType.APPLICATION_XML 타입만 허용하겠다
				.andDo(print())
				.andExpect(content().xml(XML));
	}

	//@Ignore
	@Test
	public void readJson() throws Exception {
		this.mockMvc.perform(
				post(URI, "json")
					.contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("UTF-8")
					.content("{ \"foo\": \"bar\" }".getBytes()))
		.andDo(print())
				.andExpect(content().string("Read from JSON: JavaBean {foo=[bar], fruit=[apple]}"));
	}

//	@Ignore
	@Test
	public void writeJson() throws Exception {
		this.mockMvc.perform(get(URI, "json").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.foo").value("bar"))
				.andExpect(jsonPath("$.fruit").value("apple"));
	}

//	@Ignore
	@Test
	public void writeJson2() throws Exception {
		this.mockMvc.perform(get(URI, "json").accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(jsonPath("$.foo").value("bar"))
				.andExpect(jsonPath("$.fruit").value("apple"));
	}

	private static String ATOM_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			"<feed xmlns=\"http://www.w3.org/2005/Atom\"><title>My Atom feed</title></feed>";

//	@Ignore
	@Test
	public void readAtom() throws Exception {
		this.mockMvc.perform(
				post(URI, "atom")
					.contentType(MediaType.APPLICATION_ATOM_XML)
						.characterEncoding("UTF-8")
					.content(ATOM_XML.getBytes()))
				.andDo(print())
				.andExpect(content().string("Read My Atom feed"));
	}

//	@Ignore
	@Test
	public void writeAtom() throws Exception {
		this.mockMvc.perform(get(URI, "atom").accept(MediaType.APPLICATION_ATOM_XML))
				.andExpect(content().xml(ATOM_XML));
	}

//	@Ignore
	@Test
	public void readRss() throws Exception {

		String rss = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <rss version=\"2.0\">" +
				"<channel><title>My RSS feed</title></channel></rss>";

		this.mockMvc.perform(
				post(URI, "rss")
					.contentType(MediaType.valueOf("application/rss+xml"))
						.characterEncoding("UTF-8")
					.content(rss.getBytes()))
				.andDo(print())
				.andExpect(content().string("Read My RSS feed"));
	}

//	@Ignore
	@Test
	public void writeRss() throws Exception {

		String rss = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				"<rss version=\"2.0\"><channel><title>My RSS feed</title><link>http://localhost:8080/mvc-showcase/rss</link><description>Description</description></channel></rss>";

		this.mockMvc.perform(get(URI, "rss").accept(MediaType.valueOf("application/rss+xml")))
				.andDo(print())
				.andExpect(content().xml(rss));
	}

}
