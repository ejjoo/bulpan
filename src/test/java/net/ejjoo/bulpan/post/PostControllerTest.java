package net.ejjoo.bulpan.post;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration()
@ActiveProfiles("local")
public class PostControllerTest {
	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;

	private String buildUrlEncodedFormEntity(String... params) {
		if( (params.length % 2) > 0 ) {
			throw new IllegalArgumentException("Need to give an even number of parameters");
		}
		StringBuilder result = new StringBuilder();
		for (int i=0; i<params.length; i+=2) {
			if( i > 0 ) {
				result.append('&');
			}
			try {
				result.
						append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
						append('=').
						append(URLEncoder.encode(params[i+1], StandardCharsets.UTF_8.name()));
			}
			catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return result.toString();
	}

	@BeforeEach
	void setup() {
		this.mockMvc= MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}

	@Test
	public void welcome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/post/list")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void create() throws Exception {
		Map<String, String> reqBody = new HashMap<>();
		reqBody.put("title", "test");
		reqBody.put("content", "test");
		mockMvc.perform(MockMvcRequestBuilders.post("/post")
				.content(buildUrlEncodedFormEntity("title", "test", "content", "test"))
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
