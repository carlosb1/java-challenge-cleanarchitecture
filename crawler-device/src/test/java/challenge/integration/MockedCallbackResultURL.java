package challenge.integration;

import static org.junit.Assert.assertTrue;

import challenge.entities.AnalysedURL.Status;
import challenge.usecases.CallbackResultURL;

public class MockedCallbackResultURL implements CallbackResultURL {
	private Status expectedResult;

	public MockedCallbackResultURL(Status expectedResult) {
		this.expectedResult = expectedResult;
	}

	public void setExpectedResult(Status expectedResult) {
		this.expectedResult = expectedResult;
	}

	@Override
	public void onResult(Status result) {
		assertTrue(expectedResult == result);
	}

}
