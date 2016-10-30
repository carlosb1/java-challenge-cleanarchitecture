package challenge;

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

@RestController
@RequestMapping("/analyseurls/v1.0")
public class URLController {
	@RequestMapping(value = "/urls/", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestBody AnalysedURLDto analysedUrl, UriComponentsBuilder ucBuilder) {

		// userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		// TODO check this visibility for analysed URL
		headers.setLocation(ucBuilder.path("/urls/{id}").buildAndExpand(analysedUrl.id).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/urls/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AnalysedURLDto> get(@PathVariable String id, ModelMap model) {

		AnalysedURLDto url = new AnalysedURLDto();
		if (url == null) {
			// System.out.println("User with id " + id + " not found");
			return new ResponseEntity<AnalysedURLDto>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<AnalysedURLDto>(url, HttpStatus.OK);

	}

}
