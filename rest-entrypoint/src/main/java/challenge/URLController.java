package challenge;

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
import org.springframework.web.util.UriComponentsBuilder;

import challenge.usecases.AddAndAnalyseURL;
import challenge.usecases.GetUpdatedURL;

//TODO add spring validators
@RestController
@RequestMapping("/analyseurls/v1.0")
public class URLController implements InitializingBean, DisposableBean {
	AddAndAnalyseURL urlAnalyser;
	GetUpdatedURL getUpdaterURL;

	@Autowired
	public URLController(AddAndAnalyseURL urlAnalyser, GetUpdatedURL getUpdaterURL) {
		this.urlAnalyser = urlAnalyser;
		this.getUpdaterURL = getUpdaterURL;
	}

	@RequestMapping(value = "/urls", method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public ResponseEntity<Void> add(@RequestBody URLToAnalyse urlToAnalyse, UriComponentsBuilder ucBuilder) {
		urlAnalyser.register(urlToAnalyse.getUrl());
		HttpHeaders headers = new HttpHeaders();
		// TODO check this visibility for analysed URL
		// headers.setLocation(ucBuilder.path("/urls/{id}").buildAndExpand(urlToAnalyse.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@Override
	public void destroy() throws Exception {
		this.urlAnalyser.stopCrawl();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.urlAnalyser.startCrawl();
	}

	/*
	 * @RequestMapping(value = "/urls/{id}", method = RequestMethod.GET,
	 * produces = MediaType.APPLICATION_JSON_VALUE) public
	 * ResponseEntity<AnalysedURLDto> get(@PathVariable String id, ModelMap
	 * model) {
	 * 
	 * AnalysedURLDto url = toDto(getUpdaterURL.getUpdatedURL(id)); if (url ==
	 * null) { return new ResponseEntity<AnalysedURLDto>(HttpStatus.NOT_FOUND);
	 * } return new ResponseEntity<AnalysedURLDto>(url, HttpStatus.OK);
	 * 
	 * }
	 */

}
