package com.malsolo.mercury.spring.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.malsolo.mercury.spring.domain.Event;

public class EventRepositoryTest extends AbstractIntegrationTest {

	final Logger logger = LoggerFactory.getLogger(EventRepositoryTest.class);

	@Autowired
	private EventRepository eventRepository;
	
	@Test
	public void testMarkerMethod() {
	}

	@Test
	public void testSave() {
		Event event = getNewTransientEvent();
		assertNull(event.getId());
		eventRepository.save (event);
		assertNotNull(event.getId());
		eventsIds.add(event.getId());
	}

	@Test
	public void testFindById() {
		Event event = eventRepository.findOne(eventsIds.get(0));
		assertNotNull(event);
		logger.debug("{}", event);
	}

	@Test
	public void testFindAll() {
		Iterable<Event> events = eventRepository.findAll();
		assertNotNull(events);
		Iterator<Event> eventsIterator = events.iterator();
		assertNotNull(eventsIterator);
		assertTrue(eventsIterator.hasNext());
		logger.debug("Events");
	}

	public static Event getNewTransientEvent() {
		Event event = new Event();
		event.setCodeType(1);
		event.setData("data");
	    event.setDate(new Date());
		return event;
	}

}
