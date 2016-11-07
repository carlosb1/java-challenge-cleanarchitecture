package challenge.usecases;

import java.util.List;

import challenge.entities.AnalysedURL;

public interface ModifyURL {
	List<AnalysedURL> save(List<AnalysedURL> url);

	AnalysedURL save(AnalysedURL url);
}
