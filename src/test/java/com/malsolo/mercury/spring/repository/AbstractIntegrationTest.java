package com.malsolo.mercury.spring.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.malsolo.mercury.spring.ApplicationConfiguration;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfiguration.class })
public abstract class AbstractIntegrationTest {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MongoClient mongoClient;

	@Autowired
	String databaseName;
	
	protected List<String> typesIds = new ArrayList<>();
	protected List<String> eventsIds = new ArrayList<>();
	protected List<String> alarmsIds = new ArrayList<>();
	
	@Rule
	public TestName name = new TestName();

	@Before
	public void setUp() {
		
		logger.debug("Setting up {} ", name.getMethodName());
		
		DB db = mongoClient.getDB(databaseName);

		logger.debug("Creating types...");
		DBCollection types = db.getCollection("type");
		for (int i = 0; i < 10; i++) {
			BasicDBObject newType = new BasicDBObject()
			.append("code", i)
			.append("description", "Type " + i)
			.append("active", true);
			types.insert(newType);
			logger.debug("Created type {}", newType);
			typesIds.add(newType.getString("_id"));
		}
		logger.debug("Creating types. Done. Created {} types.", typesIds.size());
		
		logger.debug("Creating events...");
		DBCollection events = db.getCollection("event");
		for (int i = 0; i < 10; i++) {
			BasicDBObject newEvent = new BasicDBObject()
			.append("codeType", i)
			.append("data", RandomStringUtils.randomAlphabetic(64))
			.append("date", new Date());
			events.insert(newEvent);
			logger.debug("Created event {}", newEvent);
			eventsIds.add(newEvent.getString("_id"));
		}
		logger.debug("Creating events. Done. Created {} events.", eventsIds.size());
		
		logger.debug("Creating alarms...");
		DBCollection alarms = db.getCollection("alarm");
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			BasicDBObject newAlarm = new BasicDBObject()
			.append("data", RandomStringUtils.randomAlphabetic(64))
			.append("date", new Date());
			BasicDBObject queryTypeById = new BasicDBObject("_id", typesIds.get(random.nextInt(typesIds.size())));
			DBCursor typeCursor = types.find(queryTypeById);
			if (typeCursor.hasNext()) {
				newAlarm.put("type", typeCursor.next());
			}
			BasicDBObject queryEventById = new BasicDBObject("_id", eventsIds.get(random.nextInt(eventsIds.size())));
			DBCursor eventCursor = events.find(queryEventById);
			if (eventCursor.hasNext()) {
				BasicDBList eventos = new BasicDBList();
				eventos.add(eventCursor.next());
				newAlarm.put("events", eventos);
			}
			alarms.insert(newAlarm);
			logger.debug("Created alarm {}", newAlarm);
			alarmsIds.add(newAlarm.getString("_id"));
		}
		logger.debug("Creating events. Done. Created {} alarms.", alarmsIds.size());

		logger.debug("Setting up {}. Done. ", name.getMethodName());
	}

	@After
	public void tearDown() {

		logger.debug("Tearing down {} ", name.getMethodName());
		
		DB db = mongoClient.getDB(databaseName);
		
		alarmsIds = remove(db, "alarm", db.getCollection("alarm"), alarmsIds);
		eventsIds = remove(db, "event", db.getCollection("event"), eventsIds);
		typesIds = remove(db, "type", db.getCollection("type"), typesIds);

		logger.debug("Tearing down {}. Done. ", name.getMethodName());

	}
	
	private List<String> remove(DB db, String collectionName, DBCollection dbCollection, List<String> ids) {
		logger.debug("Removing {}s...", collectionName);
		long count = dbCollection.getCount();
		long deleted = 0;
		//BulkWriteOperation builder = dbCollection.initializeOrderedBulkOperation();
		for (String id : ids) {
			logger.debug("Removing {}s, removing ID {} ...", collectionName, id);
			//builder.find(new BasicDBObject("_id", new ObjectId(id))).removeOne();
			DBObject dbObject =  dbCollection.findOne(new BasicDBObject("_id", new ObjectId(id)));
			dbCollection.remove(dbObject);
			deleted++;
		}
		logger.debug("Removing {}s. Done, removed {} from {} ", collectionName, deleted, count);
		return new ArrayList<>();
	}

}
