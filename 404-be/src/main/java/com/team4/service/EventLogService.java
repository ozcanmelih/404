package com.team4.service;

import com.amazonaws.services.rekognition.model.CompareFacesResult;
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

    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    public void logFaceDetectionResult(Candidate candidate, List<FaceDetail> faceList){
        EventLog log = new EventLog();
        log.setResult((double)faceList.size());
        log.setEventType(EventTypeConstants.FACE_COUNT);
        eventLogRepository.save(log);
    }

    public void logFaceMatchResult(Candidate candidate, CompareFacesResult compareFacesResult){

    }
}
