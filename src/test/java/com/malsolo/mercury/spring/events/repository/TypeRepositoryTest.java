package com.malsolo.mercury.spring.events.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.malsolo.mercury.spring.events.domain.Type;

public class TypeRepositoryTest extends AbstractIntegrationTest {
	
	final Logger logger = LoggerFactory.getLogger(TypeRepositoryTest.class);

	@Autowired @Qualifier("typeRepositoryMongoDbJavaDriver")
	private TypeRepository typeRepository;
	
	@Test
	public void testMarkerMethod() {
	}

	@Test
	public void testSave() {
		Type type = getNewTransientType();
		assertNull(type.getId());
		typeRepository.save(type);
		assertNotNull(type.getId());
		typesIds.add(type.getId());
	}
	
	@Test
	public void testFindById() {
		Type type = typeRepository.findById(typesIds.get(0));
		assertNotNull(type);
		logger.debug("{}", type);
	}

	@Test
	public void testFindAll() {
		List<Type> types = typeRepository.findAll();
		assertNotNull(types);
		assertTrue(types.size() >= typesIds.size());
		logger.debug("{} tipos", types.size());
	}
	
	@Test
	public void testFindByCode() {
		typeRepository.findByCode(1);
	}
	
	public static Type getNewTransientType() {
		Type type = new Type();
		type.setCode(1);
		type.setDescription("description");
		type.setActive(true);
		return type;
	}
	
}
