package challenge.entrypoints;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import challenge.AppConfig;
import challenge.MongoConfig;
import challenge.URLController;
import challenge.URLToAnalyse;
import challenge.usecases.AddAndAnalyseURL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfig.class, AppConfig.class })
@WebAppConfiguration
public class URLControllerTest {

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		String value = map.writeValueAsString(r);
		return value.getBytes();
	}

	// private AddAndAnalyseURL addAndAnalyseURL = mock(AddAndAnalyseURL.class);
	// private GetUpdatedURL getUpdaterURL = mock(GetUpdatedURL.class);
	@Autowired
	private AddAndAnalyseURL addAndAnalyseURL;
	private MockMvc mockMvc;

	private final List<URLToAnalyse> URL_MULTIPLE_WEBS = new LinkedList<URLToAnalyse>() {
		{
			this.add(new URLToAnalyse("http://www.google.com", 1));
			this.add(new URLToAnalyse("http://www.lavanguardia.com", 2));

		}
	};

	@Test
	public void addURLThenReturnsId() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new URLController(addAndAnalyseURL)).build();
		byte[] data = toJson(URL_MULTIPLE_WEBS);
		// MvcResult result =
		mockMvc.perform(post("/analyseurls/v1.0/urls").contentType(MediaType.APPLICATION_JSON).content(data)).andExpect(status().isCreated()).andReturn();
		// Wait for insert in database
		Thread.sleep(5000);
	}
}
