package challenge.entities;

import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.springframework.data.annotation.Id;

public class AnalysedURL {

	public static enum Status {
		NOTVISITED, TRUE, FALSE, ERROR
	};

	@Id
	private String id;

	private URL url;
	private Status status;

	private Date createdDate;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public URL getUrl() {
		return url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public AnalysedURL() {
	}

	public AnalysedURL(URL url, Status status, Date date) {
		this.url = url;
		this.status = status;
		this.createdDate = date;
	}

	public static AnalysedURL makeNotVisitedURL(URL url) {
		return new AnalysedURL(url, Status.NOTVISITED, Calendar.getInstance().getTime());
	}

	@Override
	public String toString() {
		return "AnalysedURL [id=" + id + ", url=" + url + ", status=" + status + "]";
	}

}
