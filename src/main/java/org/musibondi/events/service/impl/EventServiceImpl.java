package org.musibondi.events.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.musibondi.events.Util.EventDataUtil;
import org.musibondi.events.dal.EventDAL;
import org.musibondi.events.model.EventData;
import org.musibondi.events.model.EventStatus;
import org.musibondi.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDAL eventDAL;

	@Override
	public String createEvent(EventData eventData) {

		String id = EventDataUtil.generateEventId();


		eventData.setEventId(id);
		eventData.setStatus(EventStatus.PLANNING);

		eventDAL.createEvent(eventData);

		return id;
	}

	@Override
	public List<EventData> getEventDataByUserId(String userId) {
		return eventDAL.getEventDataByUserId(userId);
	}


	public EventDAL getEventDAL() {
		return eventDAL;
	}

	public void setEventDAL(EventDAL eventDAL) {
		this.eventDAL = eventDAL;
	}

	
	

}
