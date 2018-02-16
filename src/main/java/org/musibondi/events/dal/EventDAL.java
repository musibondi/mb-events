package org.musibondi.events.dal;

import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.model.EventData;

import java.util.List;

public interface EventDAL {

    Boolean createEvent(EventData eventData)  throws PersistenceException;
    List<EventData> getEventDataByUserId(String userId) throws PersistenceException;
}
