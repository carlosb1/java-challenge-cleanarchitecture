package challenge.usecases;

import challenge.entities.AnalysedURL;

// TODO move this class to the model interface
public interface CallbackResultURL {

	public void onResult(AnalysedURL analysedURL);

}
