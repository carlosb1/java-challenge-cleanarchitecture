package challenge.usecases;

public interface CrawlURL {
	public void addUrl(String url, CallbackResultURL onResult);

	public void start();

	public void stop() throws InterruptedException;

}
