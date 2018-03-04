package org.musibondi.events.dal.impl;

import com.mongodb.WriteResult;
import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.dal.EventDAL;
import org.musibondi.events.model.EventData;
import org.musibondi.events.model.User;
import org.musibondi.events.model.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("eventDAL")
public class EventDALImpl  implements EventDAL {

	private static final Logger logger = LoggerFactory
			.getLogger(EventDALImpl.class);

	public static final String EVENT_DATA_COLLECTION_NAME = "eventData";
	@Autowired
	private MongoOperations mongoOperations;
	
    public Boolean createEvent(EventData eventData) throws PersistenceException {

    	try {
			mongoOperations.save(eventData , EVENT_DATA_COLLECTION_NAME);
		} catch (Exception e){
			throw new PersistenceException(e);
		}

		return true;
    }

	@Override
	public EventData getEventDataById(String eventId)  throws PersistenceException {

    	EventData result;

		try {
			result = mongoOperations.findOne(new Query(Criteria.where("eventId").is(eventId)),
					EventData.class, EVENT_DATA_COLLECTION_NAME);
		} catch (Exception e){
			logger.error("Fail getting event list by eventId: " + eventId, e);
			throw new PersistenceException(e);
		}


		return result;
	}

	@Override
	public List<EventData> getEventDataAttendantByUserId(String userId) throws PersistenceException {
		List<EventData> results;

		try {
			results = mongoOperations.find(new Query(Criteria.where("userId").is(userId)),
					EventData.class, EVENT_DATA_COLLECTION_NAME);
		} catch (Exception e){
			logger.error("Fail getting event list by userId: " + userId, e);
			throw new PersistenceException(e);
		}


		return results;
	}

	@Override
	public List<EventData> getEventDataOwnerByUserId(String userId) throws PersistenceException {
		List<EventData> results;

		try {
			//results = mongoOperations.find(new Query(Criteria.where("userId").is(userId)),
			//		EventData.class, EVENT_DATA_COLLECTION_NAME);
            results = mongoOperations.find(new Query().addCriteria(
                    Criteria.where("ownerList").all(userId)
            ),EventData.class, EVENT_DATA_COLLECTION_NAME);
		} catch (Exception e){
			logger.error("Fail getting event list by userId: " + userId, e);
			throw new PersistenceException(e);
		}


		return results;
	}

    @Override
    public Boolean addUserToEvent(String userId, String eventId, UserType userType)throws PersistenceException {

        User user = new User();
        user.setUserId(userId);

        String listToPush = "ownerList";
        if(UserType.ATTENDEE.equals(userType)){
            listToPush = "attendeeList";
        }
        WriteResult wr = mongoOperations.updateFirst(
                Query.query(Criteria.where("eventId").is(eventId)),
                new Update().push(listToPush, user),
                EVENT_DATA_COLLECTION_NAME);
        return wr.isUpdateOfExisting();
    }

    public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
}
