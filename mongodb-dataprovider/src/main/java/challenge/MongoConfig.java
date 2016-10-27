package challenge;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@EnableMongoRepositories("challenge")
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(locations = "classpath:application.properties", ignoreUnknownFields = true, prefix = "spring.data.mongodb")
public class MongoConfig extends AbstractMongoConfiguration {

	private String database;
	private String host;

	public MongoConfig() {
	}

	@Override
	protected String getDatabaseName() {
		return database;
	}

	public void setDatabaseName(final String databaseName) {
		this.database = databaseName;
	}

	@Override
	@Bean
	public MongoClient mongo() throws Exception {
		final MongoClient client = new MongoClient(host);
		client.setWriteConcern(WriteConcern.SAFE);
		return client;
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(new MongoClient(host), database);
	}

	@Override
	public String toString() {
		return "[MongoConfig]";
	}

}
