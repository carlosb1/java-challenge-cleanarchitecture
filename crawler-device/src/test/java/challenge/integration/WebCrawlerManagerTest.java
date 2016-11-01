package challenge.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import challenge.WebCrawlerManager;
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
	public void crawlOnePageNotValidThenReturnsResultFalse() throws InterruptedException {
		CallbackResultURL onResult = new MockedCallbackResultURL(Status.FALSE);
		crawlerManager.addUrl("http://google.com", onResult);
	}

	@Test
	public void crawlOnePageValidThenReturnsResultTrue() throws InterruptedException {
		CallbackResultURL onResult = new MockedCallbackResultURL(Status.TRUE);
		crawlerManager.addUrl("http://lavanguardia.com", onResult);
	}

	@Test
	public void crawlMultipleWebsThenResultsAreFalseAndTrue() throws InterruptedException {
		CallbackResultURL onResultVanguardia = new MockedCallbackResultURL(Status.TRUE);
		CallbackResultURL onResultGoogle = new MockedCallbackResultURL(Status.FALSE);
		crawlerManager.addUrl("http://lavanguardia.com", onResultVanguardia);
		crawlerManager.addUrl("http://google.com", onResultGoogle);
	}

}
