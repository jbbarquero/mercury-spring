package com.malsolo.mercury.spring.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.malsolo.mercury.spring.domain.Type;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Repository("typeRepositoryMongoDbJavaDriver")
public class TypeRepositoryMongoDbJavaDriverImpl implements TypeRepository {
	
	final Logger logger = LoggerFactory.getLogger(TypeRepositoryMongoDbJavaDriverImpl.class);
	
	private static final String ID_FIELD = "_id";
	private static final String COLLECTION_NAME = Type.class.getSimpleName().toLowerCase();

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
		DBCollection types = db.getCollection(COLLECTION_NAME);
		BasicDBObject newType = new BasicDBObject()
			.append("code", type.getCode())
			.append("description", type.getDescription())
			.append("active", type.getActive());
		types.save(newType);
		type.setId(newType.getString("_id"));
	}
	
	/* (non-Javadoc)
	 * @see com.malsolo.mercury.spring.repository.TypeRepository#findById(java.lang.Long)
	 */
	@Override
	public Type findById(String id) {
		DB db = mongoClient.getDB(databaseName);
		DBCollection types = db.getCollection(COLLECTION_NAME);
		BasicDBObject query = new BasicDBObject(ID_FIELD, new ObjectId(id));
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
		DBCollection types = db.getCollection(COLLECTION_NAME);
		DBCursor cursor = types.find();
		List<Type> result = new ArrayList<>();
		while (cursor.hasNext()) {
			result.add(createFromDocument(cursor.next()));
		}
		return result;
	}
	
	@Override
	public Type findByCode(Integer code) {
		DB db = mongoClient.getDB(databaseName);
		DBCollection types = db.getCollection(COLLECTION_NAME);
		BasicDBObject query = new BasicDBObject("code", code);
		DBCursor cursor = types.find(query);
		if (cursor.hasNext()) {
			return createFromDocument(cursor.next());
		}
		return null;
	}

	private Type createFromDocument(DBObject dbObject) {
		logger.debug("Creating Type from document {} ", (dbObject != null ? dbObject.toString() : "null"));
		Type type = new Type();
		type.setId(dbObject.get(ID_FIELD).toString());
		type.setCode(Integer.valueOf(dbObject.get("code").toString()));
		type.setDescription(dbObject.get("description").toString());
		type.setActive(Boolean.valueOf(dbObject.get("active").toString()));
		return type;
	}

}
