package challenge;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import challenge.entities.AnalysedURL;
import challenge.entities.AnalysedURL.Status;
import challenge.usecases.GetURL;
import challenge.usecases.GetUpdatedURL;

public class GetUpdateURLTest {
	private GetURL getURL = mock(GetURL.class);

	private GetUpdatedURL getUpdateURL;

	@Before
	public void setUp() {
		getUpdateURL = new GetUpdatedURL(getURL);
	}

	@Test
	public void getURLWhenIsNotVisitedURLThenReturnStatus() throws MalformedURLException {
		when(getURL.findOne("1")).thenReturn(AnalysedURL.makeNotVisitedURL(new URL("http://www.test.com")));
		AnalysedURL analysedURL = getUpdateURL.getUpdatedURL("1");
		assertTrue(analysedURL.getStatus() == Status.NOTVISITED);
	}

	@Test
	public void getURLWhenIsVisitedURLThenReturnsFound() throws MalformedURLException {
		when(getURL.findOne("1")).thenReturn(new AnalysedURL(new URL("http://www.test.com"), Status.TRUE));
		AnalysedURL analysedURL = getUpdateURL.getUpdatedURL("1");
		assertTrue(analysedURL.getStatus() == Status.TRUE);
	}

	@Test
	public void getURLWhenIsVisitedURLThenReturnsNotFound() throws MalformedURLException {
		when(getURL.findOne("1")).thenReturn(new AnalysedURL(new URL("http://www.test.com"), Status.FALSE));
		AnalysedURL analysedURL = getUpdateURL.getUpdatedURL("1");
		assertTrue(analysedURL.getStatus() == Status.FALSE);
	}

	@Test
	public void getURLWhenIsVisitedURLThenReturnsError() throws MalformedURLException {
		when(getURL.findOne("1")).thenReturn(new AnalysedURL(new URL("http://www.test.com"), Status.ERROR));
		AnalysedURL analysedURL = getUpdateURL.getUpdatedURL("1");
		assertTrue(analysedURL.getStatus() == Status.ERROR);
	}

}
