package com.team4.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.rekognition.model.FaceDetail;
import com.team4.jpa.entity.Candidate;
import com.team4.service.CandidateService;
import com.team4.service.FaceService;

@RestController
public class CandidateCheckController {

	private static final Logger logger = LoggerFactory.getLogger(CandidateCheckController.class);
	
	private FaceService faceService;
	private CandidateService candidateService;
	
	public CandidateCheckController(FaceService faceService, CandidateService candidateService) {
        this.faceService = faceService;
        this.candidateService = candidateService;
	}
	
	
	@PostMapping("/setup-candidate")
	public ResponseEntity<Candidate> faceDetection(@RequestParam("jiraId") String candidateJiraId) throws Exception {
		Candidate candidate = candidateService.setupCandidate(candidateJiraId);
		
		return new ResponseEntity<>(candidate, HttpStatus.OK);
	}
}
