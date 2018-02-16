package org.musibondi.events.controller;

import org.apache.commons.lang3.StringUtils;
import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.model.EventData;
import org.musibondi.events.model.EventType;
import org.musibondi.events.request.EventCreationRequest;
import org.musibondi.events.response.BaseResponse;
import org.musibondi.events.response.EventCreationResponse;
import org.musibondi.events.service.EventService;
import org.musibondi.events.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventCreationController {

	private static final Logger logger = LoggerFactory
			.getLogger(EventCreationController.class);

	@Autowired
	private EventService eventService;
	
	@RequestMapping(value = "/api/v1/createEvent/",
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse createEvent(
			@RequestBody EventCreationRequest request) {

		BaseResponse response = new BaseResponse();

		if(isValidRequest(request, response)){

			response.setStatusCode(0);
			response.setStatusMessage("Todo bien amigo");

			EventData eventData = new EventData();
			
			eventData.setEndDate(request.getEndDate());
			eventData.setPlaylistId(request.getPlaylistId());
			eventData.setStartDate(request.getStartDate());
			eventData.setTags(request.getTags());
			eventData.setType(EventType.getBy(request.getType()));
			eventData.setUserId(request.getUserId());
			eventData.setType(EventType.getBy(request.getType()));

			try {

				EventCreationResponse eventCreationResponse = new EventCreationResponse();
				String id = eventService.createEvent(eventData);

				eventCreationResponse.setEventId(id);

				response.setData(eventCreationResponse);

			} catch (PersistenceException e){
				response.setStatusCode(1000);
				response.setStatusMessage("DatabaseError");
			}
			

			


		};

		logger.debug(request.toString());

		return response;

	}


	private boolean isValidRequest(EventCreationRequest request,
			BaseResponse response) {
		// TODO: Add userId validation, playlistId validation, dates validations

        Boolean isValidRequest = true;

        StringBuilder responseBuilder = new StringBuilder();

		if(StringUtils.isBlank(request.getType()) || EventType.getBy(request.getType()) ==null){
		    isValidRequest = false;
		    responseBuilder.append("The event type is not valid. ");
        }

        if(!isValidRequest){
		    response.setStatusCode(4404);
		    response.setStatusMessage(responseBuilder.toString());
        }
		return isValidRequest;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
}
