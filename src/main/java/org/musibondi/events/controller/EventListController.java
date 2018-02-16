package org.musibondi.events.controller;

import org.apache.commons.lang3.StringUtils;
import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.model.EventData;
import org.musibondi.events.request.EventCreationRequest;
import org.musibondi.events.response.BaseResponse;
import org.musibondi.events.response.EventCreationResponse;
import org.musibondi.events.response.EventListResponse;
import org.musibondi.events.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventListController {

	private static final Logger logger = LoggerFactory
			.getLogger(EventListController.class);

	@Autowired
	private EventService eventService;
	
	@RequestMapping(value = "/api/v1/geteventbyuserid/{userId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse getEventsByUserId(
			@PathVariable("userId") String userId) {

		BaseResponse response = new BaseResponse();

		if(isValidRequest(userId, response)){

			response.setStatusCode(0);
			response.setStatusMessage("Todo bien amigo");

			try {

				EventListResponse eventListResponse = new EventListResponse();
				List<EventData> eventDataList = eventService.getEventDataByUserId(userId);

				eventListResponse.addAll(eventDataList);

				response.setData(eventListResponse);

			} catch (PersistenceException e){
				response.setStatusCode(1000);
				response.setStatusMessage("DatabaseError");
			}

		};

		logger.debug("Requested events by user:" + userId);

		return response;

	}

	private boolean isValidRequest(String userId,
			BaseResponse response) {

		Boolean isValid = true;

		if(StringUtils.isBlank(userId)){

			isValid = false;
			response.setStatusCode(100);
			response.setStatusMessage("Invalid request, the userId not be null or empty");
		}
		return isValid;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
