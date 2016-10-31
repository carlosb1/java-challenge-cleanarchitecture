package challenge.usecases;

import java.net.MalformedURLException;
import java.net.URL;

import challenge.entities.AnalysedURL;

public class URLAnalyser {
	private final ModifyURL modifyURL;

	public URLAnalyser(ModifyURL modifyURL) {
		this.modifyURL = modifyURL;
	}

	public void register(String address) {
		// AnalysedURL urlToAnalyse =
		try {
			URL url = new URL(address);
			AnalysedURL urlToAnalyse = AnalysedURL.makeNotVisitedURL(url);
			modifyURL.save(urlToAnalyse);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// modifyURL.save(url);

	}

}
