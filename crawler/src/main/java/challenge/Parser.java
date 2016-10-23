package challenge;

import java.io.IOException;

import org.jsoup.nodes.Document;

public class Parser {
	public final static String TAG_NEWS = "NEWS";
	public final static String TAG_NOTICIAS = "NOTICIAS";

	public boolean findTags(Document document) throws IOException {
		if (document == null) {
			throw new IOException("The document object is null");
		}
		String title = document.title().toUpperCase();
		return (title.contains(TAG_NEWS) || title.contains(TAG_NOTICIAS));
	}

}
