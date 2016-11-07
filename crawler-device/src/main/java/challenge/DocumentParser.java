package challenge;

import org.jsoup.nodes.Document;

import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;
import challenge.usecases.CallbackResultURL;

public class DocumentParser {
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
		this.callbackResult.onResult(urlToAnalyse);

	}

}
