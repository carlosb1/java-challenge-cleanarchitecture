package challenge.entities;

import java.net.URL;

import org.springframework.data.annotation.Id;

public class AnalysedURL {

	public static enum Status {
		NOTVISITED, TRUE, FALSE, ERROR
	};

	@Id
	public String id;
	public URL url;
	private Status status;

	public AnalysedURL() {
	}

	public AnalysedURL(URL url, Status status) {
		this.url = url;
		this.status = status;
	}

	public static AnalysedURL makeNotVisitedURL(URL url) {
		return new AnalysedURL(url, Status.NOTVISITED);
	}

	@Override
	public String toString() {
		return "AnalysedURL [id=" + id + ", url=" + url + ", status=" + status + "]";
	}

}
