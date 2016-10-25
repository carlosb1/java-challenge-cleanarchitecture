package challenge.models;

import java.net.URL;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "urls")
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

	@Override
	public String toString() {
		return "AnalysedURL [id=" + id + ", url=" + url + ", status=" + status + "]";
	}
}
