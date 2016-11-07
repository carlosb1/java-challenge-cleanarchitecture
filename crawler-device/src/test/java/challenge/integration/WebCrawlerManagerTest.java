package challenge.integration;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import challenge.WebCrawlerManager;
import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;
import challenge.usecases.CallbackResultURL;

public class WebCrawlerManagerTest {

	private WebCrawlerManager crawlerManager;

	@Before
	public void setUp() {
		crawlerManager = new WebCrawlerManager();
		crawlerManager.start();
	}

	@After
	public void tearDown() throws InterruptedException {
		crawlerManager.stop();
	}

	@Test
	public void crawlOnePageNotValidThenReturnsResultFalse() throws InterruptedException, MalformedURLException {
		List<AnalysedURL> urlsToAnalyse = new ArrayList<AnalysedURL>(Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL("http://www.google.com"))));

		CallbackResultURL onResult = new MockedCallbackResultURL(Status.FALSE);
		crawlerManager.addUrls(urlsToAnalyse, onResult);
	}

	@Test
	public void crawlOnePageValidThenReturnsResultTrue() throws InterruptedException, MalformedURLException {
		List<AnalysedURL> urlsToAnalyse = new ArrayList<AnalysedURL>(Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL("http://lavanguardia.com"))));
		CallbackResultURL onResult = new MockedCallbackResultURL(Status.TRUE);
		crawlerManager.addUrls(urlsToAnalyse, onResult);
	}

	@Test
	public void crawlMultipleWebsThenResultsAreFalseAndTrue() throws InterruptedException, MalformedURLException {
		List<AnalysedURL> urlsToAnalyseWithoutResults = new ArrayList<AnalysedURL>(
				Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL("http://www.google.com"))));
		List<AnalysedURL> urlsToAnalyseWithResults = new ArrayList<AnalysedURL>(
				Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL("http://lavanguardia.com"))));
		CallbackResultURL onResultVanguardia = new MockedCallbackResultURL(Status.TRUE);
		CallbackResultURL onResultGoogle = new MockedCallbackResultURL(Status.FALSE);
		crawlerManager.addUrls(urlsToAnalyseWithResults, onResultVanguardia);
		crawlerManager.addUrls(urlsToAnalyseWithoutResults, onResultGoogle);
	}

}
