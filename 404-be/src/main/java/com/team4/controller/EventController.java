package com.team4.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.service.EventLogService;

@RestController
public class EventController {
	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	private EventLogService eventService;
	
	public EventController(EventLogService eventService) {
		this.eventService = eventService;
		logger.info("Event Controller initialized");
	}
	
	@PostMapping("/events/{event}/{userId}")
	public ResponseEntity<String> eventLogger(@PathVariable("event") String eventType, @PathVariable("userId") Long candidateId, @RequestParam("eventStr") String eventString) throws Exception {
		
		eventService.logPageEvents(candidateId, eventType, eventString);
		logger.info("init event log for user: {}, event: {}", candidateId, eventType);
		return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
	}
}
