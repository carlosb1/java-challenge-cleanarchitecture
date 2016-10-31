package challenge.usecases;

import challenge.entities.AnalysedURL;

public interface GetURL {
	AnalysedURL findOne(String id);

}
