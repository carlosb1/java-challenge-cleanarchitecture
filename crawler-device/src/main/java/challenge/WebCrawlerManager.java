package challenge;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
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
			LOGGER.log(Level.INFO, "Executing new crawler thread");
			crawler.execute();
		}

	}

	private final LinkedBlockingQueue<EventCrawler> linksToVisit;
	private final ExecutorService execService;

	private static final int TIME_WAIT = 10;
	private static final int MAX_THREADS = 64;
	private final ExecutorService poolExecutor;

	public WebCrawlerManager() {
		this(MAX_THREADS);
	}

	public WebCrawlerManager(int maxThreads) {
		linksToVisit = new LinkedBlockingQueue<EventCrawler>();
		execService = Executors.newFixedThreadPool(maxThreads);
		poolExecutor = Executors.newSingleThreadExecutor();

	}

	public void addUrls(List<AnalysedURL> urls, CallbackResultURL onResult) {
		linksToVisit.add(EventCrawler.makeEventAnalyseURLs(urls, onResult));

	}

	public void start() {
		poolExecutor.submit(() -> {
			try {
				while (true) {
					EventCrawler infoThread;
					infoThread = linksToVisit.take();
					if (infoThread.isStop()) {
						return;
					}
					if (infoThread != null) {
						execService.execute(new ThreadWebCrawler(infoThread));
					}
				}
			} catch (Exception e) {
				LOGGER.log(Level.WARNING, e.getMessage(), e);
			}
		});

	}

	public void stop() throws InterruptedException {
		linksToVisit.add(EventCrawler.makeStopEvent());
		Thread.sleep(TIME_WAIT);

	}

}
