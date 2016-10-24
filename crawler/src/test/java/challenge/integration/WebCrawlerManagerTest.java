package challenge.integration;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import challenge.CallbackResult;
import challenge.WebCrawlerManager;

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
		CallbackResult onResult = new CallbackResult();
		crawlerManager.addUrl("http://google.com", onResult);
		assertTrue(onResult.isFound() == CallbackResult.Status.NOTVISITED);
		Thread.sleep(1000);
		assertTrue(onResult.isFound() == CallbackResult.Status.FALSE);
	}

	@Test
	public void crawlOnePageValidThenReturnsResultTrue() throws InterruptedException {
		CallbackResult onResult = new CallbackResult();
		crawlerManager.addUrl("http://lavanguardia.com", onResult);
		assertTrue(onResult.isFound() == CallbackResult.Status.NOTVISITED);
		Thread.sleep(1000);
		assertTrue(onResult.isFound() == CallbackResult.Status.TRUE);

	}

	@Test
	public void crawlMultipleWebsThenResultsAreFalseAndTrue() throws InterruptedException {
		CallbackResult onResultVanguardia = new CallbackResult();
		CallbackResult onResultGoogle = new CallbackResult();
		crawlerManager.addUrl("http://lavanguardia.com", onResultVanguardia);
		crawlerManager.addUrl("http://google.com", onResultGoogle);
		Thread.sleep(1000);
		assertTrue(onResultVanguardia.isFound() == CallbackResult.Status.TRUE);
		assertTrue(onResultGoogle.isFound() == CallbackResult.Status.FALSE);
	}

}
