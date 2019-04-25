package expense.manager.mongodb.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import expense.manager.model.Expense;

@Component
public class MongoDBConfig {

	private static final String MONGO_DB_HOST = "localhost";
	private static final int MONGO_DB_PORT = 27017;
	private static final String DATABASE_NAME = "expenseManager";

	@Bean
	public MongoTemplate mongoConfig() throws IOException {
		//Embedded MongoDB
		EmbeddedMongoFactoryBean factoryBean = new EmbeddedMongoFactoryBean();
		factoryBean.setBindIp(MONGO_DB_HOST);
		factoryBean.setPort(MONGO_DB_PORT);
		// connect to external MongoDB
		// MongoClient mongoClient = new MongoClient(MONGO_DB_HOST, MONGO_DB_PORT);
		MongoClient mongoClient = factoryBean.getObject();
		MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, DATABASE_NAME);
		mongoTemplate.dropCollection(Expense.class);	// TODO must be removed
		return mongoTemplate;
	}
}
