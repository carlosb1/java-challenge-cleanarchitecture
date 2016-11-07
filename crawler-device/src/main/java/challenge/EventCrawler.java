package challenge;

import java.util.ArrayList;
import java.util.List;

import challenge.entities.AnalysedURL;
import challenge.usecases.CallbackResultURL;

public class EventCrawler {

	private List<AnalysedURL> urls;

	private CallbackResultURL result;
	private final boolean isStop;

	private static class NullCallbackResultURL implements CallbackResultURL {
		public void onResult(AnalysedURL analysedURL) {
		}

	}

	private EventCrawler() {
		this.urls = new ArrayList<AnalysedURL>();
		this.result = new NullCallbackResultURL();
		this.isStop = true;

	}

	private EventCrawler(final List<AnalysedURL> urls, final CallbackResultURL result) {
		this.urls = urls;
		this.result = result;
		this.isStop = false;
	}

	public final boolean isStop() {
		return this.isStop;
	}

	public List<AnalysedURL> getUrls() {
		return urls;
	}

	public CallbackResultURL getResult() {
		return result;
	}

	public static EventCrawler makeStopEvent() {
		return new EventCrawler();
	}

	public static EventCrawler makeEventAnalyseURLs(final List<AnalysedURL> urls, final CallbackResultURL result) {
		return new EventCrawler(urls, result);

	}

}
