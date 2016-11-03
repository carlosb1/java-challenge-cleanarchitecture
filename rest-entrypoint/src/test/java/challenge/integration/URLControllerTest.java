package challenge.integration;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import challenge.URLController;
import challenge.usecases.AddAndAnalyseURL;
import challenge.usecases.GetUpdatedURL;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = { AppConfig.class })
//@WebAppConfiguration
public class URLControllerTest {

	private AddAndAnalyseURL addAndAnalyseURL = mock(AddAndAnalyseURL.class);
	private GetUpdatedURL getUpdaterURL = mock(GetUpdatedURL.class);

	private MockMvc mockMvc;

	@Test
	public void addURLThenReturnsId() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new URLController(addAndAnalyseURL, getUpdaterURL)).build();
	}
}
