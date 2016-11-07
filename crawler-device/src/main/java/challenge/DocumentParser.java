package challenge;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.nodes.Document;

import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;
import challenge.usecases.CallbackResultURL;

public class DocumentParser {
	private final static Logger LOGGER = Logger.getLogger(DocumentParser.class.getName());
	public final static String TAG_NEWS = "NEWS";
	public final static String TAG_NOTICIAS = "NOTICIAS";
	CallbackResultURL callbackResult;

	public DocumentParser(CallbackResultURL callbackResult) {
		this.callbackResult = callbackResult;
	}

	public void execute(Document document, AnalysedURL urlToAnalyse) {
		if (document == null) {
			urlToAnalyse.setStatus(Status.ERROR);
			return;
		}
		String title = document.title().toUpperCase();
		if (title.contains(TAG_NEWS) || title.contains(TAG_NOTICIAS)) {
			urlToAnalyse.setStatus(Status.TRUE);
		} else {
			urlToAnalyse.setStatus(Status.FALSE);
		}
		LOGGER.log(Level.INFO, "Updating database with url:" + urlToAnalyse.url.toString() + " and status: " + urlToAnalyse.getStatus());
		this.callbackResult.onResult(urlToAnalyse);

	}

}
