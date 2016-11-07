package challenge.usecases.contracts;

import java.util.List;

import challenge.entities.AnalysedURL;

public interface CrawlURL {
	public void addUrls(List<AnalysedURL> urls, CallbackResultURL onResult);

	public void start();

	public void stop() throws InterruptedException;

}
