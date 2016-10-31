package challenge.usecases;

import java.net.URL;

import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;

public class UpdateResultURL implements CallbackResultURL {

	private final ModifyURL modifyURL;
	private final URL url;

	public UpdateResultURL(ModifyURL modifyURL, URL url) {
		this.modifyURL = modifyURL;
		this.url = url;
	}

	@Override
	public void onResult(Status result) {
		modifyURL.save(new AnalysedURL(url, result));
	}

}
