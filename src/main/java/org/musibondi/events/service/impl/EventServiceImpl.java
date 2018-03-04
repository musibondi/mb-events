package org.musibondi.events.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.musibondi.events.Util.EventDataUtil;
import org.musibondi.events.dal.EventDAL;
import org.musibondi.events.dal.LogDataDAL;
import org.musibondi.events.model.*;
import org.musibondi.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component("eventService")
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDAL eventDAL;

	@Autowired
	private LogDataDAL logDataDAL;


	@Override
	public String createEvent(EventData eventData, User user) {

		String id = EventDataUtil.generateEventId();


		eventData.setEventId(id);
		eventData.setStatus(EventStatus.PLANNING);

		eventDAL.createEvent(eventData);

		LogData logData = new LogData();
		logData.setEventId(id);

		logData.setLastUpdateTimestamp(LocalDateTime.now());
		logData.setUserId(user.getUserId());
		logDataDAL.addLogData(logData);

		return id;
	}

	@Override
	public Boolean addAttendeeToEvent(String userId, String eventId) {
		return eventDAL.addUserToEvent(userId, eventId, UserType.ATTENDEE);
	}

	@Override
	public Boolean addOwnerToEvent(String userId, String eventId) {
		return eventDAL.addUserToEvent(userId, eventId, UserType.OWNER);
	}

	@Override
	public List<EventData> getEventAttendantBy(String userId) {
		return eventDAL.getEventDataAttendantByUserId(userId);
	}

	@Override
	public List<EventData> getEventOwnerBy(String userId) {
		return eventDAL.getEventDataOwnerByUserId(userId);
	}


	public EventDAL getEventDAL() {
		return eventDAL;
	}

	public void setEventDAL(EventDAL eventDAL) {
		this.eventDAL = eventDAL;
	}

	public LogDataDAL getLogDataDAL() {
		return logDataDAL;
	}

	public void setLogDataDAL(LogDataDAL logDataDAL) {
		this.logDataDAL = logDataDAL;
	}
}
