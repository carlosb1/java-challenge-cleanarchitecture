package challenge.entities;

import java.net.URL;

public class AnalysedURL {

	public static enum Status {
		NOTVISITED, TRUE, FALSE, ERROR
	};

	public String id;
	public URL url;
	private Status status;

	public AnalysedURL() {
	}

	public AnalysedURL(URL url, Status status) {
		this.url = url;
		this.status = status;
	}

	@Override
	public String toString() {
		return "AnalysedURL [id=" + id + ", url=" + url + ", status=" + status + "]";
	}
}
