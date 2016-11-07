package challenge;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import challenge.entities.AnalysedURL;
import challenge.usecases.AddAndAnalyseURL;
import challenge.usecases.CallbackResultURL;
import challenge.usecases.CrawlURL;
import challenge.usecases.ModifyURL;

public class URLAddAndAnalyseTest {

	private static final String WEB_MARFEELIZABLE = "http://www.lavanguardia.com";
	private CrawlURL crawlURL = mock(CrawlURL.class);
	private ModifyURL modifyURL = mock(ModifyURL.class);

	private AddAndAnalyseURL addAndAnalyseURL;

	@Before
	public void setUp() {
		addAndAnalyseURL = new AddAndAnalyseURL(modifyURL, crawlURL);
	}

	@Test
	public void addURLThenModifyDBWithInformation() throws MalformedURLException {
		List<AnalysedURL> urlsToAnalyse = new ArrayList<AnalysedURL>(Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL(WEB_MARFEELIZABLE))));

		addAndAnalyseURL.register(urlsToAnalyse);

		ArgumentCaptor<List> verifyURLs = thenAddedURLToCrawl();
		assertCrawlURL(verifyURLs);
	}

	private void assertCrawlURL(ArgumentCaptor<List> verifyURL) {
		assertTrue(((AnalysedURL) verifyURL.getValue().get(0)).url.toString().equals(WEB_MARFEELIZABLE));
	}

	private ArgumentCaptor<List> thenAddedURLToCrawl() {
		ArgumentCaptor<List> verifyURL = ArgumentCaptor.forClass(List.class);
		ArgumentCaptor<CallbackResultURL> callbackResult = ArgumentCaptor.forClass(CallbackResultURL.class);
		verify(crawlURL).addUrls(verifyURL.capture(), callbackResult.capture());
		return verifyURL;
	}

}
