package org.musibondi.events.dal.impl;

import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.dal.EventDAL;
import org.musibondi.events.model.EventData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventDAL")
public class EventDALImpl  implements EventDAL {

	private static final Logger logger = LoggerFactory
			.getLogger(EventDALImpl.class);

	public static final String EVENT_DATA = "eventData";
	@Autowired
	private MongoOperations mongoOperations;
	
    public Boolean createEvent(EventData eventData) throws PersistenceException {

    	try {
			mongoOperations.save(eventData ,EVENT_DATA);
		} catch (Exception e){
			throw new PersistenceException(e);
		}

		return true;
    }

	@Override
	public List<EventData> getEventDataByUserId(String userId)  throws PersistenceException {

    	List<EventData> results;

		try {
			results = mongoOperations.find(new Query(Criteria.where("userId").is(userId)),EventData.class, EVENT_DATA);
		} catch (Exception e){
			logger.error("Fail getting event list by userId: " + userId, e);
			throw new PersistenceException(e);
		}


		return results;
	}

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
}
