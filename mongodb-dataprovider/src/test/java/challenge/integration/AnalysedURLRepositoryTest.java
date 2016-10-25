package challenge.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.config.RepositoryConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = RepositoryConfiguration.class)
public class AnalysedURLRepositoryTest {
	// @Autowired
	// AnalysedURLRepository repository;
	@Autowired
	private ApplicationContext applicationContext;

	@Test
	public void addOneURLThenIsUploadCorrectly() {
		System.out.println("Hello world");
		// assertTrue(repository.count() == 0);
	}
}
