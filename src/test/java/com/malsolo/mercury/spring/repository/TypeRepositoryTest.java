package com.malsolo.mercury.spring.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.malsolo.mercury.spring.ApplicationConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public class TypeRepositoryTest {
	
	@Test
	public void testMarkerMethod() {
	}

}
