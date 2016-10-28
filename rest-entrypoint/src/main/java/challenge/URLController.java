package challenge;

import org.springframework.web.bind.annotation.RestController;

import challenge.entities.AnalysedURL;

@RestController
public class URLController {

	// TODO add get urls!
	public AnalysedURL get() {
		return new AnalysedURL();
	}
}
