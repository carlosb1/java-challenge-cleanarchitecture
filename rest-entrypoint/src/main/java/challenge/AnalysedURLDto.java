package challenge;

public class AnalysedURLDto {

	public static enum Status {
		NOTVISITED, TRUE, FALSE, ERROR
	};

	public String id;
	public String url;
	private Status status;

	public AnalysedURLDto() {
	}

	public AnalysedURLDto(String url, Status status) {
		this.url = url;
		this.status = status;
	}

}
