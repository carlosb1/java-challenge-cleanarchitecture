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
import challenge.usecases.AddAndAnalyseURL;
import challenge.usecases.CallbackResultURL;
import challenge.usecases.CrawlURL;
import challenge.usecases.ModifyURL;

public class URLAddAndAnalyseTest {

	private CrawlURL crawlURL = mock(CrawlURL.class);
	private ModifyURL modifyURL = mock(ModifyURL.class);

	private AddAndAnalyseURL addAndAnalyseURL;

	@Before
	public void setUp() {
		addAndAnalyseURL = new AddAndAnalyseURL(modifyURL, crawlURL);
	}

	@Test
	public void addURLThenModifyDBWithInformation() throws MalformedURLException {

		addAndAnalyseURL.register("http://www.lavanguardia.com");

		ArgumentCaptor<AnalysedURL> analysedURL = thenURLIsAddedInDB();
		assertAddedNewURL(analysedURL);

		ArgumentCaptor<String> verifyURL = thenAddedURLToCrawl();

		assertCrawlURL(verifyURL);
	}

	private void assertCrawlURL(ArgumentCaptor<String> verifyURL) {
		assertTrue(verifyURL.getValue().equals("http://www.lavanguardia.com"));
		assertTrue(verifyURL.getValue().equals("http://www.lavanguardia.com"));
	}

	private ArgumentCaptor<String> thenAddedURLToCrawl() {
		ArgumentCaptor<String> verifyURL = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<CallbackResultURL> callbackResult = ArgumentCaptor.forClass(CallbackResultURL.class);
		verify(crawlURL).addUrl(verifyURL.capture(), callbackResult.capture());
		return verifyURL;
	}

	private void assertAddedNewURL(ArgumentCaptor<AnalysedURL> analysedURL) throws MalformedURLException {
		assertTrue(analysedURL.getValue().url.equals(new URL("http://www.lavanguardia.com")));
		assertTrue(analysedURL.getValue().getStatus() == Status.NOTVISITED);
	}

	private ArgumentCaptor<AnalysedURL> thenURLIsAddedInDB() {
		ArgumentCaptor<AnalysedURL> analysedURL = ArgumentCaptor.forClass(AnalysedURL.class);
		verify(modifyURL).save(analysedURL.capture());
		return analysedURL;
	}

}
