package br.java.mvp.infrastructure;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.pojo.PojoCodecProvider.builder;

import com.mongodb.MongoClientOptions;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoDbConfig {

	@Bean
	public MongoClientOptions mongoClientOption() {
		final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(builder().automatic(true).build()));
		return MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build();
	}
}