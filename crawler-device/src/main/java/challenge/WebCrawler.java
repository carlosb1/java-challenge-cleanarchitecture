package challenge;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public final class WebCrawler {
	private final String url;
	private final Parser parser;

	public WebCrawler(String url, Parser parser) {
		this.url = url;
		this.parser = parser;
	}

	// TODO create generic solutions, factory? builder, apply rules
	public boolean execute() throws IOException, IllegalArgumentException {
		Document document = Jsoup.connect(this.url).get();
		return this.parser.findTags(document);
	}

}
