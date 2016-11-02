package challenge;

public class AnalysedURLDto {

	public static enum Status {
		NOTVISITED, TRUE, FALSE, ERROR
	};

	private final String id;
	private final String url;
	private final Status status;

	public AnalysedURLDto(String id, String url, Status status) {
		this.url = url;
		this.status = status;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public Status getStatus() {
		return status;
	}

}
