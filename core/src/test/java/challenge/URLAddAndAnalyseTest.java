package challenge;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;
import challenge.usecases.CrawlURL;
import challenge.usecases.ModifyURL;
import challenge.usecases.URLAddAndAnalyse;

public class URLAddAndAnalyseTest {
	// GetDeviceDetails getDeviceDetails = mock(GetDeviceDetails.class);

	private CrawlURL crawlURL = mock(CrawlURL.class);
	private ModifyURL modifyURL = mock(ModifyURL.class);

	private URLAddAndAnalyse addAndAnalyseURL;

	@Before
	public void setUp() {
		addAndAnalyseURL = new URLAddAndAnalyse(modifyURL, crawlURL);
	}

	@Test
	public void addURLThenModifyDBWithInformation() throws MalformedURLException {

		addAndAnalyseURL.register("http://www.lavanguardia.com");
		/* save url before to analyse */
		ArgumentCaptor<AnalysedURL> argument = ArgumentCaptor.forClass(AnalysedURL.class);
		verify(modifyURL).save(argument.capture());
		assertTrue(argument.getAllValues().get(0).url.equals(new URL("http://www.lavanguardia.com")));
		assertTrue(argument.getAllValues().get(0).getStatus() == Status.NOTVISITED);
		/* add crawlURL receive correct url */
	}
}
