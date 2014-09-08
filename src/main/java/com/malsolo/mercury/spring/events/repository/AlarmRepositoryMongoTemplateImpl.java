package com.malsolo.mercury.spring.events.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.malsolo.mercury.spring.events.domain.Alarm;

@Repository
public class AlarmRepositoryMongoTemplateImpl implements AlarmRepository {

	@Autowired
	private MongoOperations mongoOperations;

	/* (non-Javadoc)
	 * @see com.malsolo.mercury.spring.repository.AlarmRepository#save(com.malsolo.mercury.spring.domain.Alarm)
	 */
	@Override
	public void save(Alarm alarm) {
//		mongoOperations.save(alarm);
		mongoOperations.insert(alarm);
	}

	/* (non-Javadoc)
	 * @see com.malsolo.mercury.spring.repository.AlarmRepository#findById(java.lang.Long)
	 */
	@Override
	public Alarm findById(String id) {
		return mongoOperations.findOne(query(where("id").is(id)), Alarm.class);
		
	}

	/* (non-Javadoc)
	 * @see com.malsolo.mercury.spring.repository.AlarmRepository#findAll()
	 */
	@Override
	public List<Alarm> findAll() {
		return mongoOperations.findAll(Alarm.class);
	}

}
