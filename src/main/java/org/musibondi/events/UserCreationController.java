package org.musibondi.events;

import org.musibondi.events.request.EventCreationRequest;
import org.musibondi.events.response.BaseResponse;
import org.musibondi.events.response.EventCreationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCreationController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserCreationController.class);

	@RequestMapping(value = "/api/v1/createUser/", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public BaseResponse createUser(
			@RequestBody EventCreationRequest request) {

		BaseResponse response = new BaseResponse();
		response.setStatusCode(0);
		response.setStatusMessage("Todo bien amigo");

		EventCreationResponse eventCreationResponse = new EventCreationResponse();
		eventCreationResponse.setEventId(request.toString());

		response.setData(eventCreationResponse);
		
		logger.debug(request.toString());

		return response;

	}

}
