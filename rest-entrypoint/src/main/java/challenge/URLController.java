package challenge;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import challenge.entities.AnalysedURL;
import challenge.usecases.AddAndAnalyseURL;

@RestController
@RequestMapping("/analyseurls/v1.0")
public class URLController implements InitializingBean, DisposableBean {

	private final static Logger LOGGER = Logger.getLogger(URLController.class.getName());

	AddAndAnalyseURL urlAnalyser;

	@Autowired
	public URLController(AddAndAnalyseURL urlAnalyser) {
		this.urlAnalyser = urlAnalyser;
	}

	@RequestMapping(value = "/urls", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<Void> add(@RequestBody List<URLToAnalyse> urlToAnalyse) {
		LOGGER.log(Level.INFO, "Applying post request");
		List<AnalysedURL> urls = dtosToAnalysedURLs(urlToAnalyse);
		urlAnalyser.register(urls);
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	private List<AnalysedURL> dtosToAnalysedURLs(List<URLToAnalyse> urlsToAnalyse) {

		List<AnalysedURL> urls = new ArrayList<AnalysedURL>();
		for (URLToAnalyse urlToAnalyse : urlsToAnalyse) {
			String possibleURL = urlToAnalyse.getUrl();
			try {
				URL url = new URL(possibleURL);
				urls.add(AnalysedURL.makeNotVisitedURL(url));
			} catch (MalformedURLException e) {
				LOGGER.log(Level.WARNING, "URL maltformed received from POST rest petition", e);
			}
		}
		return urls;
	}

	@Override
	public void destroy() throws Exception {
		LOGGER.log(Level.INFO, "Destroying URL controller");
		this.urlAnalyser.stopCrawl();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.log(Level.INFO, "Starting URL controller");
		this.urlAnalyser.startCrawl();
	}
}
