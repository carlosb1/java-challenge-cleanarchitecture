package challenge;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;
import challenge.usecases.contracts.CallbackResultURL;

public class DocumentParserTest {

	private DocumentParser parser;

	private CallbackResultURL callbackResult = mock(CallbackResultURL.class);

	@Before
	public void setUp() {
		parser = new DocumentParser(callbackResult);
	}

	@Test
	public void parseWhenItdoesnotContainTagsThenItDoesnotFind() throws IOException {
		Document document = Document.createShell("");
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.FALSE);
	}

	@Test
	public void parseWhenItContainTagNEWSThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(DocumentParser.TAG_NEWS);
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	@Test
	public void parseWhenItContainTagsNEWSUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(DocumentParser.TAG_NEWS.toUpperCase());
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	@Test
	public void parseWhenItContainTagNOTICIASThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(DocumentParser.TAG_NOTICIAS);
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	@Test
	public void parseWhenItContainTagsNOTICIASUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(DocumentParser.TAG_NOTICIAS.toUpperCase());
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	@Test
	public void parseWhenItContainTagsNOTICIASFirstUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("Noticias");
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	@Test
	public void parseWhenItContainTagsNEWSFirstUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("News");
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	@Test
	public void parseWhenItContainTagsNOTICIASSomeUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("Noticias");
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	@Test
	public void parseWhenItContainTagsNEWSSomeUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("News");
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.TRUE);
	}

	public void parseWhenDocumentsIsNullThenFails() throws IOException {
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(null, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.ERROR);
	}

	@Test
	public void parseWhenItDoesntContainTagInTitleTagsThenItDoesntFind() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title");
		AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(new URL("http://wwww.example.com"));
		parser.execute(document, urlToAnalyse);
		assertTrue(urlToAnalyse.getStatus() == Status.FALSE);
	}

}
