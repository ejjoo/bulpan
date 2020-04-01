package net.ejjoo.bulpan.post;

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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration()
@ActiveProfiles("local")
public class PostControllerTest {
	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		this.mockMvc= MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}
	@Test
	public void welcome() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
