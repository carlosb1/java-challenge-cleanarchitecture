package challenge.dataproviders;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.junit.Before;
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
public class AnalysedURLRepositoryTest {
	@Autowired
	AnalysedURLRepository repository;

	@Before
	public void setUp() {
		repository.deleteAll();
	}

	@Test
	public void addOneURLThenIsUploadCorrectly() throws MalformedURLException {
		AnalysedURL url = new AnalysedURL(new URL("https://www.google.com"), AnalysedURL.Status.TRUE, new Date());
		repository.save(url);
		List<AnalysedURL> info = repository.findAll();
		assertTrue(info.size() == 1);
	}

	@Test
	public void addOneURLThenReturnsOneById() throws MalformedURLException {
		AnalysedURL url = new AnalysedURL(new URL("https://www.google.com"), AnalysedURL.Status.TRUE, new Date());
		AnalysedURL savedURL = repository.save(url);
		AnalysedURL foundURL = repository.findOne(savedURL.getId());
		assertTrue(foundURL != null);
		assertTrue(foundURL.getUrl().toString().equals("https://www.google.com"));
	}

	@Test
	public void addOneURLThenReturnsIncorrectOneById() throws MalformedURLException {
		AnalysedURL url = new AnalysedURL(new URL("https://www.google.com"), AnalysedURL.Status.TRUE, new Date());
		repository.save(url);
		AnalysedURL foundURL = repository.findOne("-1");
		assertTrue(foundURL == null);
	}

}
