package org.musibondi.events.dal;

import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.model.EventData;
import org.musibondi.events.model.User;
import org.musibondi.events.model.UserType;

import java.util.List;

public interface EventDAL {

    Boolean createEvent(EventData eventData)  throws PersistenceException;
    EventData getEventDataById(String eventId) throws PersistenceException;

    List<EventData> getEventDataAttendantByUserId(String userId) throws PersistenceException;

    List<EventData> getEventDataOwnerByUserId(String userId) throws PersistenceException;

    Boolean addUserToEvent(String userId, String eventId, UserType userType)throws PersistenceException;
}
