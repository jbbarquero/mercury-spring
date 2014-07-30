package com.malsolo.mercury.spring.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.malsolo.mercury.spring.domain.Alarm;

public class AlarmRepositoryTest extends AbstractIntegrationTest {
	
	final Logger logger = LoggerFactory.getLogger(AlarmRepositoryTest.class);
	
	@Autowired
	private AlarmRepository alarmRepository;

	@Test
	public void testMarkerMethod() {
	}

	@Test
	public void testSave() {
		alarmRepository.save(getNewTransientAlarm());
	}

	@Test
	public void testFindById() {
		Alarm alarm = alarmRepository.findById(alarmsIds.get(0));
		assertNotNull(alarm);
		logger.debug("{}", alarm);
	}

	@Test
	public void testFindAll() {
		List<Alarm> alarmas = alarmRepository.findAll();
		assertNotNull(alarmas);
		assertTrue(alarmas.size() >= alarmsIds.size());
		logger.debug("{} alarmas", alarmas.size());
	}

	public static Alarm getNewTransientAlarm() {
		Alarm alarm = new Alarm();
		alarm.setData("data");
		alarm.setDate(new Date());
		alarm.setType(null);
		alarm.setEvents(null);
		return alarm;
	}

}
