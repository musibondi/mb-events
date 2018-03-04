package org.musibondi.events.controller;

import org.apache.commons.lang3.StringUtils;
import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.model.EventData;
import org.musibondi.events.model.EventType;
import org.musibondi.events.model.User;
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

import java.util.ArrayList;

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
			ArrayList<User> ownerList = new ArrayList<>();
			ownerList.add(request.getOwner());
			eventData.setOwnerList(ownerList);
			eventData.setType(EventType.getBy(request.getType()));
			eventData.setAttendeeList(new ArrayList<>());

			try {

				EventCreationResponse eventCreationResponse = new EventCreationResponse();
				String id = eventService.createEvent(eventData, request.getOwner());

				eventCreationResponse.setEventId(id);

				response.setData(eventCreationResponse);

			} catch (PersistenceException e){
				response.setStatusCode(1000);
				response.setStatusMessage("DatabaseError");
			} catch (Exception e){
				response.setStatusCode(9000);
				response.setStatusMessage("General Error");
			}
			

			


		};

		logger.debug(request.toString());

		return response;

	}

	@RequestMapping(value = "/api/v1/addAttend/{userId}/Event/{eventId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse addAttendeeToEvent(
			@PathVariable("userId") String userId, @PathVariable("eventId") String eventId) {

		BaseResponse response = new BaseResponse();

		if(isValidRequest(userId, eventId)){

			response.setStatusCode(0);
			response.setStatusMessage("Todo bien amigo");

			try {


				if(!eventService.addAttendeeToEvent(userId, eventId)){

					response.setStatusCode(22);
					response.setStatusMessage("No se pudo hacer");

				}


			} catch (PersistenceException e){
				response.setStatusCode(1000);
				response.setStatusMessage("DatabaseError");
			} catch (Exception e){
				response.setStatusCode(9000);
				response.setStatusMessage("General Error");
			}





		};


		return response;

	}

    @RequestMapping(value = "/api/v1/addOwner/{userId}/Event/{eventId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BaseResponse addOwnerToEvent(
            @PathVariable("userId") String userId, @PathVariable("eventId") String eventId) {

        BaseResponse response = new BaseResponse();

        if(isValidRequest(userId, eventId)){

            response.setStatusCode(0);
            response.setStatusMessage("Todo bien amigo");

            try {


                if(!eventService.addOwnerToEvent(userId, eventId)){

                    response.setStatusCode(22);
                    response.setStatusMessage("No se pudo hacer");

                }


            } catch (PersistenceException e){
                response.setStatusCode(1000);
                response.setStatusMessage("DatabaseError");
            } catch (Exception e){
                response.setStatusCode(9000);
                response.setStatusMessage("General Error");
            }





        };


        return response;

    }

    private boolean isValidRequest(String userId, String eventId) {
		return true;
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
