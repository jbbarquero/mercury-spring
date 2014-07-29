package com.malsolo.mercury.spring.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.malsolo.mercury.spring.domain.Type;

public class TypeRepositoryTest extends AbstractIntegrationTest {
	
	final Logger logger = LoggerFactory.getLogger(TypeRepositoryTest.class);

	@Autowired
	private TypeRepository typeRepository;
	
	@Test
	public void testMarkerMethod() {
	}

	@Test
	public void testSave() {
		typeRepository.save(getNewTransientType());
	}
	
	@Test
	public void testFindById() {
		Type type = typeRepository.findById(new ObjectId().toString());
//		assertNotNull(type);
		logger.debug("{}", type);
	}

	@Test
	public void testFindAll() {
		List<Type> types = typeRepository.findAll();
		assertNotNull(types);
		assertTrue(types.size() > 0);
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
