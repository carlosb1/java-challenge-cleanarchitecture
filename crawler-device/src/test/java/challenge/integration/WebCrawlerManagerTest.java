package challenge.integration;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import challenge.WebCrawlerManager;
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
		CallbackResultURL onResult = new CallbackResultURL();
		crawlerManager.addUrl("http://google.com", onResult);
		assertTrue(onResult.isFound() == CallbackResultURL.Status.NOTVISITED);
		Thread.sleep(1000);
		assertTrue(onResult.isFound() == CallbackResultURL.Status.FALSE);
	}

	@Test
	public void crawlOnePageValidThenReturnsResultTrue() throws InterruptedException {
		CallbackResultURL onResult = new CallbackResultURL();
		crawlerManager.addUrl("http://lavanguardia.com", onResult);
		assertTrue(onResult.isFound() == CallbackResultURL.Status.NOTVISITED);
		Thread.sleep(1000);
		assertTrue(onResult.isFound() == CallbackResultURL.Status.TRUE);

	}

	@Test
	public void crawlMultipleWebsThenResultsAreFalseAndTrue() throws InterruptedException {
		CallbackResultURL onResultVanguardia = new CallbackResultURL();
		CallbackResultURL onResultGoogle = new CallbackResultURL();
		crawlerManager.addUrl("http://lavanguardia.com", onResultVanguardia);
		crawlerManager.addUrl("http://google.com", onResultGoogle);
		Thread.sleep(1000);
		assertTrue(onResultVanguardia.isFound() == CallbackResultURL.Status.TRUE);
		assertTrue(onResultGoogle.isFound() == CallbackResultURL.Status.FALSE);
	}

}
