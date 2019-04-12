package com.team4.service;

import com.amazonaws.services.rekognition.model.CompareFacesMatch;
import com.amazonaws.services.rekognition.model.CompareFacesResult;
import com.amazonaws.services.rekognition.model.ComparedFace;
import com.amazonaws.services.rekognition.model.FaceDetail;
import com.team4.constants.EventTypeConstants;
import com.team4.jpa.entity.Candidate;
import com.team4.jpa.entity.EventLog;
import com.team4.jpa.repository.EventLogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventLogService {

    private EventLogRepository eventLogRepository;
    private CandidateService candidateService;

    public EventLogService(EventLogRepository eventLogRepository, CandidateService candidateService) {
        this.eventLogRepository = eventLogRepository;
        this.candidateService = candidateService;
    }
    
    public void logPageEvents(Long candidateId, String eventType, String eventString) {
    	Candidate candidate = candidateService.findCandidateById(candidateId);
    	EventLog log = new EventLog();
        log.setEventType(eventType);
        log.setCandidate(candidate);
        
    	switch (eventType.toUpperCase()) {
		case EventTypeConstants.BROWSER_COPY:
			
			break;

		case EventTypeConstants.BROWSER_PASTE:
			
			break;
			
		default:
			break;
		}
    	
    	log.setResult(0d);
    	log.setDecision(-1);
    	log.setComparisonStr(eventString);
    	
    	eventLogRepository.save(log);
    }

    public void logFaceDetectionResult(Candidate candidate, List<FaceDetail> faceList){
        EventLog log = new EventLog();
        log.setEventType(EventTypeConstants.FACE_COUNT);
        log.setCandidate(candidate);
        boolean singlePerson = faceList.size() == 1;
        
        if(singlePerson) {
        	log.setResult(faceList.get(0).getConfidence().doubleValue());
        	log.setDecision(singlePerson && log.getResult() >= 0.9d ? 1 : 0);
        	log.setComparisonStr("faceCount: 1 and confidence: " + log.getResult() );
        } else {
        	log.setResult(0d);
        	log.setDecision(-1);
        	log.setComparisonStr("faceCount: " + faceList.size());
        }
        
        eventLogRepository.save(log);
    }

    public void logFaceMatchResult(Candidate candidate, CompareFacesResult compareFacesResult){

    	List<CompareFacesMatch> matched = compareFacesResult.getFaceMatches();
    	List<ComparedFace> unmatched 	= compareFacesResult.getUnmatchedFaces();
    	
    	EventLog log = new EventLog();
    	log.setEventType(EventTypeConstants.FACE_COMPARE);
    	log.setCandidate(candidate);
    	boolean singlePerson = matched.size() == 1 && unmatched.size() == 0;
    	
    	if(singlePerson) {
    		log.setResult(matched.get(0).getSimilarity().doubleValue());
    		log.setDecision(singlePerson && log.getResult() >= 0.9d ? 1 : 0);
    		log.setComparisonStr("matching FaceCount: 1 and confidence: " + log.getResult() );
    	} else {
    		log.setResult(0d);
    		log.setDecision(-1);
    		log.setComparisonStr("matching FaceCount: " + matched.size() + ", unmatching FaceCount:" + unmatched.size());
    	}
    	
    	eventLogRepository.save(log);
    }
}
