package challenge.integration;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import challenge.AnalysedURLRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration
public class AnalysedURLRepositoryTest {
	@Autowired
	AnalysedURLRepository repository;

	@Test
	public void addOneURLThenIsUploadCorrectly() {
		assertTrue(repository.count() == 0);
	}
}
