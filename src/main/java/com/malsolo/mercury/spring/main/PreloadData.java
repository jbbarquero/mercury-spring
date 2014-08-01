package com.malsolo.mercury.spring.main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.malsolo.mercury.spring.MongoDbRepositoryConfiguration;
import com.malsolo.mercury.spring.domain.Type;
import com.malsolo.mercury.spring.repository.TypeRepository;

@Component
@Main
public class PreloadData {
	
	final private static Logger logger = LoggerFactory.getLogger(PreloadData.class);
	
	@Autowired @Qualifier("typeRepositoryMongoDbJavaDriver") private TypeRepository typeRepository;

	public static void main(String... args) {
		
		logger.debug("BEGIN");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(MongoDbRepositoryConfiguration.class);
		context.register(ApplicationConfigurationForMains.class);
		context.refresh();
		
		PreloadData preloadData = context.getBean(PreloadData.class);
		
		logger.debug("Creating types");
		List<Type> types = preloadData.initTypes();
		logger.debug("Types created:");
		for (Type type : types) {
			logger.debug("Type:{} ", type);
		}
		
		context.close();
		
		logger.debug("END");
	}
	
	public List<Type> initTypes() {
		
		List<Type> types = new ArrayList<>();
		
		Type type = new Type();
		type.setCode(1000);
		type.setDescription("Trace type");
		type.setActive(true);
		this.typeRepository.save(type);
		types.add(type);
		
		type = new Type();
		type.setCode(2000);
		type.setDescription("Debug type");
		type.setActive(true);
		this.typeRepository.save(type);
		types.add(type);

		type = new Type();
		type.setCode(3000);
		type.setDescription("Info type");
		type.setActive(true);
		this.typeRepository.save(type);
		types.add(type);

		type = new Type();
		type.setCode(4000);
		type.setDescription("Warn type");
		type.setActive(true);
		this.typeRepository.save(type);
		types.add(type);

		type = new Type();
		type.setCode(5000);
		type.setDescription("Error type");
		type.setActive(true);
		this.typeRepository.save(type);
		types.add(type);

		return types;
	}

}
