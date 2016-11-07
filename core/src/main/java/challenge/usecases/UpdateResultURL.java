package challenge.usecases;

import challenge.entities.AnalysedURL;
import challenge.usecases.contracts.CallbackResultURL;
import challenge.usecases.contracts.ModifyURL;

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
