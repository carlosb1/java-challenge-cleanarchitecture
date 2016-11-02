package challenge.usecases;

import challenge.entities.AnalysedURL;

public class GetUpdatedURL {
	private final GetURL getURL;

	public GetUpdatedURL(GetURL getURL) {
		this.getURL = getURL;
	}

	public AnalysedURL getUpdatedURL(String id) {
		return this.getURL.findOne(id);

	}

}
