package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import challenge.usecases.AddAndAnalyseURL;
import challenge.usecases.CrawlURL;
import challenge.usecases.GetUpdatedURL;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "challenge")
public class AppConfig {

	@Autowired
	AnalysedURLRepository repository;

	@Bean
	public CrawlURL crawlURL() {
		return new WebCrawlerManager();
	}

	@Bean
	public AddAndAnalyseURL urlAnalyser(AnalysedURLRepository repository, CrawlURL crawlURL) {
		return new AddAndAnalyseURL(repository, crawlURL);

	}

	@Bean
	public GetUpdatedURL getUpdaterURL(AnalysedURLRepository repository) {
		return new GetUpdatedURL(repository);

	}

}
