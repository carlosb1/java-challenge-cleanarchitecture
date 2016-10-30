package challenge;

import java.net.URL;

public class AnalysedURLDto {

	public static enum Status {
		NOTVISITED, TRUE, FALSE, ERROR
	};

	public String id;
	public URL url;
	private Status status;

	public AnalysedURLDto() {
	}

	public AnalysedURLDto(URL url, Status status) {
		this.url = url;
		this.status = status;
	}

}
