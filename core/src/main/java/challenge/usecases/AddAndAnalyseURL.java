package challenge.usecases;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import challenge.entities.AnalysedURL;
import challenge.usecases.contracts.CrawlURL;
import challenge.usecases.contracts.ModifyURL;

public class AddAndAnalyseURL {

	private final static Logger LOGGER = Logger.getLogger(AddAndAnalyseURL.class.getName());

	private final ModifyURL modifyURL;
	private final CrawlURL crawlURL;

	public AddAndAnalyseURL(ModifyURL modifyURL, CrawlURL crawlURL) {
		this.modifyURL = modifyURL;
		this.crawlURL = crawlURL;
	}

	public void startCrawl() {
		LOGGER.log(Level.INFO, "It is starting the crawler process");
		this.crawlURL.start();
	}

	public void stopCrawl() {
		LOGGER.log(Level.INFO, "It is stopping the crawler process");
		try {
			this.crawlURL.stop();
		} catch (InterruptedException e) {
			LOGGER.log(Level.WARNING, "It ocurred some interruption in the stop process for the crawler", e);
		}
	}

	public void register(List<AnalysedURL> urlsToAnalyse) {
		crawlURL.addUrls(urlsToAnalyse, new UpdateResultURL(modifyURL));

	}

}
