package challenge;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import challenge.entities.AnalysedURL;

public final class WebCrawler {
	private final static Logger LOGGER = Logger.getLogger(WebCrawler.class.getName());
	private final List<AnalysedURL> urlsToAnalyse;
	private final DocumentParser parser;

	public WebCrawler(List<AnalysedURL> urlsToAnalyse, DocumentParser parser) {
		this.urlsToAnalyse = urlsToAnalyse;
		this.parser = parser;
	}

	public void execute() {
		for (AnalysedURL urlToAnalyse : urlsToAnalyse) {
			try {
				Document document;
				document = Jsoup.connect(urlToAnalyse.url.toString()).get();
				this.parser.execute(document, urlToAnalyse);

			} catch (IOException e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		}
	}

}
