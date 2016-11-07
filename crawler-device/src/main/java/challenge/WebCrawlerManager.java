package challenge;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import challenge.entities.AnalysedURL;
import challenge.usecases.CallbackResultURL;
import challenge.usecases.CrawlURL;

public class WebCrawlerManager implements CrawlURL {
	private final static Logger LOGGER = Logger.getLogger(WebCrawlerManager.class.getName());

	private static final class ThreadWebCrawler implements Runnable {
		private final WebCrawler crawler;
		private final EventCrawler info;

		public ThreadWebCrawler(final EventCrawler info) {
			this.info = info;
			crawler = new WebCrawler(this.info.getUrls(), new DocumentParser(this.info.getResult()));
		}

		@Override
		public void run() {
			crawler.execute();

		}

	}

	// TODO add reactive programming
	// TODO create synchronized element
	private final LinkedBlockingQueue<EventCrawler> linksToVisit;
	private final AtomicBoolean stop;
	private final ExecutorService execService;

	private static final int TIME_WAIT = 1;
	private static final int MAX_THREADS = 64;
	private final ExecutorService poolExecutor;

	public WebCrawlerManager() {
		this(MAX_THREADS);
	}

	public WebCrawlerManager(int maxThreads) {
		linksToVisit = new LinkedBlockingQueue<EventCrawler>();
		stop = new AtomicBoolean(true);
		execService = Executors.newFixedThreadPool(maxThreads);
		poolExecutor = Executors.newSingleThreadExecutor();

	}

	public void addUrls(List<AnalysedURL> urls, CallbackResultURL onResult) {
		linksToVisit.add(EventCrawler.makeEventAnalyseURLs(urls, onResult));

	}

	public void start() {
		poolExecutor.submit(() -> {
			while (true) {
				EventCrawler infoThread = linksToVisit.poll();
				if (infoThread.isStop()) {
					return;
				}
				if (infoThread != null) {
					execService.execute(new ThreadWebCrawler(infoThread));
				}
			}

		});

	}

	public void stop() throws InterruptedException {
		linksToVisit.add(EventCrawler.makeStopEvent());
		Thread.sleep(TIME_WAIT);

	}

}
