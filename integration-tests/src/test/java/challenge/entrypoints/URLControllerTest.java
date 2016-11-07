package challenge.entrypoints;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
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

	private static final String REST_POST_URL = "/analyseurls/v1.0/urls";

	private byte[] toJson(Object r) throws Exception {
		ObjectMapper map = new ObjectMapper();
		String value = map.writeValueAsString(r);
		return value.getBytes();
	}

	@Autowired
	private AddAndAnalyseURL addAndAnalyseURL;
	private MockMvc mockMvc;

	private static final List<URLToAnalyse> URL_MULTIPLE_WEBS = Arrays.asList(new URLToAnalyse("http://www.google.com", 1),
			new URLToAnalyse("http://www.lavanguardia.com", 2));

	private static final List<URLToAnalyse> URL_MULTIPLE_WEBS_INCORRECT_LINKS = Arrays.asList(new URLToAnalyse("http://asdad", 1),
			new URLToAnalyse("www.ass", 2));

	private static final List<URLToAnalyse> URL_WITHOUT_LINKS = Arrays.asList(new URLToAnalyse("", 1));

	private static final List<URLToAnalyse> EMPTY_URLS = Arrays.asList();

	@Test
	public void addCorrectURLsThenReturnStatus() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new URLController(addAndAnalyseURL)).build();
		byte[] data = toJson(URL_MULTIPLE_WEBS);
		mockMvc.perform(post(REST_POST_URL).contentType(MediaType.APPLICATION_JSON).content(data)).andExpect(status().isCreated());
		// Wait for insert in database
		Thread.sleep(2000);
	}

	@Test
	public void addIncorrectURLsThenReturnStatus() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new URLController(addAndAnalyseURL)).build();
		byte[] data = toJson(URL_MULTIPLE_WEBS_INCORRECT_LINKS);
		mockMvc.perform(post(REST_POST_URL).contentType(MediaType.APPLICATION_JSON).content(data)).andExpect(status().isCreated());
		// Wait for insert in database
		Thread.sleep(2000);
	}

	@Test
	public void addURLsWithoutLinksThenReturnStatus() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new URLController(addAndAnalyseURL)).build();
		byte[] data = toJson(URL_WITHOUT_LINKS);
		mockMvc.perform(post(REST_POST_URL).contentType(MediaType.APPLICATION_JSON).content(data)).andExpect(status().isCreated());
		// Wait for insert in database
		Thread.sleep(2000);
	}

	@Test
	public void addEmptyURLsThenReturnStatus() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new URLController(addAndAnalyseURL)).build();
		byte[] data = toJson(EMPTY_URLS);
		mockMvc.perform(post(REST_POST_URL).contentType(MediaType.APPLICATION_JSON).content(data)).andExpect(status().isCreated());
		// Wait for insert in database
		Thread.sleep(2000);
	}
}
