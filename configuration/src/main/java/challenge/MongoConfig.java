package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@EnableMongoRepositories("challenge")
@Configuration
@PropertySource(value = "classpath:application.properties")
public class MongoConfig extends AbstractMongoConfiguration {

	@Autowired
	Environment env;

	public MongoConfig() {
	}

	@Override
	@Bean
	public MongoClient mongo() throws Exception {
		String host = env.getProperty("spring.data.mongodb.host");
		final MongoClient client = new MongoClient(host);
		client.setWriteConcern(WriteConcern.SAFE);
		return client;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		String database = env.getProperty("spring.data.mongodb.databaseName");
		String host = env.getProperty("spring.data.mongodb.host");
		return new MongoTemplate(new MongoClient(host), database);
	}

	@Override
	public String toString() {
		return "[MongoConfig]";
	}

	@Override
	protected String getDatabaseName() {
		return env.getProperty("spring.data.mongodb.databaseName");
	}

}
