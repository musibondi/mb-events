package org.musibondi.events.service;

import org.musibondi.events.model.EventData;
import org.musibondi.events.model.User;

import java.util.List;

public interface EventService {

	String createEvent(EventData eventData, User user);

	Boolean addAttendeeToEvent(String userId, String eventId);

	Boolean addOwnerToEvent(String userId, String eventId);

	List<EventData> getEventAttendantBy(String userId);

	List<EventData> getEventOwnerBy(String userId);


}
