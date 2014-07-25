package com.malsolo.mercury.spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malsolo.mercury.spring.domain.Type;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Repository
public class TypeRepositoryMongoDbJavaDriverImpl implements TypeRepository {
	
	@Autowired
	String databaseName;
	
	@Autowired
	private MongoClient mongoClient;
	
	/* (non-Javadoc)
	 * @see com.malsolo.mercury.spring.repository.TypeRepository#save(com.malsolo.mercury.spring.domain.Type)
	 */
	@Override
	public void save(Type type) {
		DB db = mongoClient.getDB(databaseName);
		DBCollection types = db.getCollection(Type.class.getTypeName());
		BasicDBObject newType = new BasicDBObject("code", type.getCode())
			.append("description", type.getDescription())
			.append("active", type.getActive());
		types.save(newType);
	}
	
	/* (non-Javadoc)
	 * @see com.malsolo.mercury.spring.repository.TypeRepository#findById(java.lang.Long)
	 */
	@Override
	public Type findById(Long id) {
		DB db = mongoClient.getDB(databaseName);
		DBCollection types = db.getCollection(Type.class.getTypeName());
		BasicDBObject query = new BasicDBObject("_id", new ObjectId(id.toString()));
		DBCursor cursor = types.find(query);
		if (cursor.hasNext()) {
			return createFromDocument(cursor.next());
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.malsolo.mercury.spring.repository.TypeRepository#findAll()
	 */
	@Override
	public List<Type> findAll() {
		DB db = mongoClient.getDB(databaseName);
		DBCollection types = db.getCollection(Type.class.getTypeName());
		DBCursor cursor = types.find();
		List<Type> result = new ArrayList<>();
		while (cursor.hasNext()) {
			result.add(createFromDocument(cursor.next()));
		}
		return result;
	}
	
	private Type createFromDocument(DBObject dbObject) {
		Type type = new Type();
		type.setId(Long.valueOf(dbObject.get("_id").toString()));
		type.setCode(Integer.valueOf(dbObject.get("_id").toString()));
		type.setDescription(dbObject.get("_id").toString());
		type.setActive(Boolean.valueOf(dbObject.get("_id").toString()));
		return type;
	}

}
