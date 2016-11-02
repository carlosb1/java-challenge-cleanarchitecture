package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import challenge.entities.AnalysedURL;
import challenge.usecases.AddAndAnalyseURL;
import challenge.usecases.GetUpdatedURL;

//TODO add spring validators
@RestController
@RequestMapping("/analyseurls/v1.0")
public class URLController {
	@Autowired
	AddAndAnalyseURL urlAnalyser;
	@Autowired
	GetUpdatedURL getUpdaterURL;

	@RequestMapping(value = "/urls/", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody AnalysedURLDto analysedUrl, UriComponentsBuilder ucBuilder) {
		urlAnalyser.register(analysedUrl.getUrl());

		// userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		// TODO check this visibility for analysed URL
		headers.setLocation(ucBuilder.path("/urls/{id}").buildAndExpand(analysedUrl.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/urls/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AnalysedURLDto> get(@PathVariable String id, ModelMap model) {

		AnalysedURLDto url = toDto(getUpdaterURL.getUpdatedURL(id));
		if (url == null) {
			return new ResponseEntity<AnalysedURLDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AnalysedURLDto>(url, HttpStatus.OK);

	}

	private AnalysedURLDto toDto(AnalysedURL analysedURL) {
		AnalysedURLDto.Status statusDto;
		if (analysedURL.getStatus() == AnalysedURL.Status.TRUE) {
			statusDto = AnalysedURLDto.Status.TRUE;
		} else if (analysedURL.getStatus() == AnalysedURL.Status.FALSE) {
			statusDto = AnalysedURLDto.Status.FALSE;
		} else if (analysedURL.getStatus() == AnalysedURL.Status.NOTVISITED) {
			statusDto = AnalysedURLDto.Status.NOTVISITED;
		} else {
			statusDto = AnalysedURLDto.Status.ERROR;
		}
		return new AnalysedURLDto(analysedURL.id, analysedURL.url.toString(), statusDto);
	}

}
