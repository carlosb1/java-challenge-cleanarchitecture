package challenge;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

import challenge.usecases.CallbackResult;

public class WebCrawlerManager {
	private final static Logger LOGGER = Logger.getLogger(WebCrawlerManager.class.getName());

	private static final class InfoWebCrawler {

		private final String url;
		private final CallbackResult result;

		private InfoWebCrawler(final String url, final CallbackResult result) {
			this.url = url;
			this.result = result;
		}
	}

	private static final class ThreadWebCrawler implements Runnable {
		private final WebCrawler crawler;
		private final InfoWebCrawler info;

		public ThreadWebCrawler(final InfoWebCrawler info) {
			this.info = info;
			crawler = new WebCrawler(this.info.url, new Parser());
		}

		@Override
		public void run() {
			try {
				// TODO move callback to web crawler...
				boolean result = crawler.execute();
				this.info.result.setResult((result == true) ? CallbackResult.Status.TRUE : CallbackResult.Status.FALSE);
			} catch (IllegalArgumentException e) {
				// TODO review logs
				this.info.result.setResult(CallbackResult.Status.ERROR);
				LOGGER.log(Level.INFO, e.getMessage(), e);
			} catch (IOException e) {
				this.info.result.setResult(CallbackResult.Status.ERROR);
				LOGGER.log(Level.INFO, e.getMessage(), e);
			}

		}

	}

	// TODO add reactive programming
	// TODO create synchronized element
	private final LinkedBlockingQueue<InfoWebCrawler> linksToVisit;
	private final AtomicBoolean stop;
	private final ExecutorService execService;

	private static final int TIME_WAIT = 1;
	private static final int MAX_THREADS = 64;
	private final ExecutorService poolExecutor;

	public WebCrawlerManager() {
		this(MAX_THREADS);
	}

	public WebCrawlerManager(int maxThreads) {
		linksToVisit = new LinkedBlockingQueue<InfoWebCrawler>();
		stop = new AtomicBoolean(true);
		execService = Executors.newFixedThreadPool(maxThreads);
		poolExecutor = Executors.newSingleThreadExecutor();

	}

	public void addUrl(String url, CallbackResult onResult) {
		linksToVisit.add(new InfoWebCrawler(url, onResult));

	}

	public void start() {
		poolExecutor.submit(() -> {
			try {
				stop.set(false);
				while (!stop.get()) {
					InfoWebCrawler infoThread = linksToVisit.poll(TIME_WAIT, TimeUnit.SECONDS);
					if (infoThread != null) {
						execService.execute(new ThreadWebCrawler(infoThread));
					}
				}
			} catch (InterruptedException e) {
				LOGGER.log(Level.INFO, e.getMessage(), e);
			}

		});

	}

	public void stop() throws InterruptedException {
		stop.set(true);
		Thread.sleep(TIME_WAIT);

	}

}
