package challenge;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	private Parser parser;

	@Before
	public void setUp() {
		parser = new Parser();
	}

	@Test
	public void parseWhenItdoesnotContainTagsThenItDoesnotFind() throws IOException {
		Document document = Document.createShell("");
		assertFalse(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagNEWSThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(Parser.TAG_NEWS);
		assertTrue(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagsNEWSUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(Parser.TAG_NEWS.toUpperCase());
		assertTrue(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagNOTICIASThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(Parser.TAG_NOTICIAS);
		assertTrue(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagsNOTICIASUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text(Parser.TAG_NOTICIAS.toUpperCase());
		assertTrue(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagsNOTICIASFirstUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("Noticias");
		assertTrue(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagsNEWSFirstUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("News");
		assertTrue(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagsNOTICIASSomeUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("Noticias");
		assertTrue(parser.findTags(document));
	}

	@Test
	public void parseWhenItContainTagsNEWSSomeUpperThenItFinds() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title").text("News");
		assertTrue(parser.findTags(document));
	}

	@Test(expected = IOException.class)
	public void parseWhenDocumentsIsNullThenFails() throws IOException {
		parser.findTags(null);
	}

	@Test
	public void parseWhenItDoesntContainTagInTitleTagsThenItDoesntFind() throws IOException {
		Document document = Document.createShell("");
		document.head().appendElement("title");
		assertFalse(parser.findTags(document));
	}

}
