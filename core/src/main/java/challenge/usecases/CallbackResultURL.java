package challenge.usecases;

import challenge.entities.AnalysedURL.Status;

// TODO move this class to the model interface
public interface CallbackResultURL {

	public void onResult(Status result);

}
