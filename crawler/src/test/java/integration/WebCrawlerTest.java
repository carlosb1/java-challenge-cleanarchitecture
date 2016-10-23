package integration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import challenge.Parser;
import challenge.WebCrawler;

public class WebCrawlerTest {

	public static class MockParserLoadDocument extends Parser {
		public boolean findTags(Document document) throws IOException {
			if (document == null || !document.hasText()) {
				Assert.fail();
			}
			return false;
		}
	}

	public static class MockParserSuccess extends Parser {
		public boolean findTags(Document document) throws IOException {
			boolean result = super.findTags(document);
			assertTrue(result);
			return result;
		}
	}

	private WebCrawler webCrawler;

	@Test
	public void connectWhenWebPageThenParseValues() {
		webCrawler = new WebCrawler("http://www.google.com", new MockParserLoadDocument());
		try {
			assertFalse(webCrawler.execute());
		} catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void connectWhenWebPageDoesntExistThenParseValues() {
		webCrawler = new WebCrawler("noexist", new Parser());
		try {
			webCrawler.execute();
			Thread.sleep(2000);
			Assert.fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void connectWhenWebPageWithTagsThenParseValues() throws IllegalArgumentException, IOException, InterruptedException {
		webCrawler = new WebCrawler("http://lavanguardia.com", new MockParserSuccess());
		webCrawler.execute();
		Thread.sleep(2000);

	}

	@Test
	public void connectWhenWebPageSSLWithTagsThenParseValues() throws IllegalArgumentException, IOException, InterruptedException {
		webCrawler = new WebCrawler("https://www.google.com", new MockParserLoadDocument());
		webCrawler.execute();
		Thread.sleep(2000);

	}

}
