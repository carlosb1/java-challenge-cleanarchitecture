package challenge.usecases;

import challenge.entities.AnalysedURL;

public class URLGetUpdatedURL {
	private final GetURL getURL;

	public URLGetUpdatedURL(GetURL getURL) {
		this.getURL = getURL;
	}

	public AnalysedURL getUpdatedURL(String id) {
		return this.getURL.findOne(id);

	}

}
