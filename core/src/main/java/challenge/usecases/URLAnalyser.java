package challenge.usecases;

import java.net.MalformedURLException;
import java.net.URL;

import challenge.entities.AnalysedURL;

public class URLAnalyser {
	private final ModifyURL modifyURL;
	private final CrawlURL crawlURL;

	public URLAnalyser(ModifyURL modifyURL, CrawlURL crawlURL) {
		this.modifyURL = modifyURL;
		this.crawlURL = crawlURL;
	}

	public void register(String address) {
		// AnalysedURL urlToAnalyse =
		try {
			URL url = new URL(address);
			AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(url);
			modifyURL.save(urlToAnalyse);
			// TODO add factory for UpdateResultURL
			crawlURL.addUrl(url.toString(), new UpdateResultURL(modifyURL, url));

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// modifyURL.save(url);

	}

}
