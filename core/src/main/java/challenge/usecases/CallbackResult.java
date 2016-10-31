package challenge.usecases;

// TODO move this class to the model interface
public class CallbackResult {
	private Status found;

	public static enum Status {
		FALSE, TRUE, NOTVISITED, ERROR
	};

	public CallbackResult() {
		found = Status.NOTVISITED;
	}

	public void setResult(Status result) {
		found = result;
	}

	public Status isFound() {
		return found;
	}

}
