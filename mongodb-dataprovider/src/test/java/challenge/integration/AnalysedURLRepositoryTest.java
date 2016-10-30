package challenge.integration;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import challenge.AnalysedURLRepository;
import challenge.MongoConfig;
import challenge.entities.AnalysedURL;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfig.class })
// TODO added mocks for database
// TODO add tests for save an replace
public class AnalysedURLRepositoryTest {
	@Autowired
	AnalysedURLRepository repository;

	@Test
	public void addOneURLThenIsUploadCorrectly() throws MalformedURLException {
		repository.deleteAll();
		AnalysedURL url = new AnalysedURL(new URL("https:://www.google.com"), AnalysedURL.Status.TRUE);
		repository.save(url);
		List<AnalysedURL> info = repository.findAll();
		assertTrue(info.size() == 1);
	}
}
