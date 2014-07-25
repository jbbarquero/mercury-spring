package com.malsolo.mercury.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.Repository;

import com.malsolo.mercury.spring.repository.AlarmRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan
@EnableMongoRepositories(basePackageClasses = {AlarmRepository.class}, excludeFilters = {@Filter(type = FilterType.ASSIGNABLE_TYPE, value = Repository.class)})
public class ApplicationConfiguration {
	
	private static final String MONGODB_DATABASE_NAME = "mercury";
	
    @Bean
	public String databaseName() {
		return MONGODB_DATABASE_NAME;
	}
	
    @Bean
    public Mongo mongo() throws Exception {
        Mongo mongo = new MongoClient();
        mongo.setWriteConcern(WriteConcern.SAFE); //I asume is Acknowledged (w:1). See http://docs.mongodb.org/manual/core/write-concern/
        return mongo;
    }
	
    @Bean
	public MongoOperations mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), databaseName());
	}

}
