package org.musibondi.events.service;

import org.musibondi.events.model.EventData;

import java.util.List;

public interface EventService {

	String createEvent(EventData eventData);

	List<EventData> getEventDataByUserId(String userId);

}
