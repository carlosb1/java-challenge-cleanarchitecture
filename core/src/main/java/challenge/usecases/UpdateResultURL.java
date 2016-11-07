package challenge.usecases;

import challenge.entities.AnalysedURL;

public class UpdateResultURL implements CallbackResultURL {

	private final ModifyURL modifyURL;

	public UpdateResultURL(ModifyURL modifyURL) {
		this.modifyURL = modifyURL;
	}

	@Override
	public void onResult(AnalysedURL analysedURL) {
		modifyURL.save(analysedURL);
	}

}
