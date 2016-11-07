package challenge.devices;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import challenge.DocumentParser;
import challenge.WebCrawler;
import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;
import challenge.usecases.contracts.CallbackResultURL;

public class WebCrawlerTest {

	private WebCrawler webCrawler;
	private CallbackResultURL mockedCallbackResultURL = mock(CallbackResultURL.class);

	@Test
	public void connectWhenWebPageThenParseValues() throws InterruptedException, MalformedURLException {
		List<AnalysedURL> urlsToAnalyse = new ArrayList<AnalysedURL>(Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL("http://www.google.com"))));
		WebCrawler webCrawler = new WebCrawler(urlsToAnalyse, new DocumentParser(mockedCallbackResultURL));
		webCrawler.execute();
		Thread.sleep(1000);
		assertTrue(urlsToAnalyse.get(0).getStatus() == Status.FALSE);
	}

	@Test
	public void connectWhenWebPageWithTagsThenParseValues() throws InterruptedException, MalformedURLException {
		List<AnalysedURL> urlsToAnalyse = new ArrayList<AnalysedURL>(Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL("http://lavanguardia.com"))));
		webCrawler = new WebCrawler(urlsToAnalyse, new DocumentParser(mockedCallbackResultURL));
		webCrawler.execute();
		Thread.sleep(2000);
		assertTrue(urlsToAnalyse.get(0).getStatus() == Status.TRUE);
	}

	@Test
	public void connectWhenWebPageSSLWithTagsThenParseValues() throws InterruptedException, MalformedURLException {
		List<AnalysedURL> urlsToAnalyse = new ArrayList<AnalysedURL>(Arrays.asList(AnalysedURL.makeNotVisitedURL(new URL("https://www.google.com"))));
		webCrawler = new WebCrawler(urlsToAnalyse, new DocumentParser(mockedCallbackResultURL));
		webCrawler.execute();
		Thread.sleep(1000);
		assertTrue(urlsToAnalyse.get(0).getStatus() == Status.FALSE);

	}

}
