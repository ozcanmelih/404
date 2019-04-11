package com.team4.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.jpa.entity.Candidate;
import com.team4.service.CandidateService;

@RestController
public class CandidateCheckController {

    private CandidateService candidateService;

    public CandidateCheckController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/setup-candidate")
    public ResponseEntity<Candidate> faceDetection(@RequestParam("jiraId") String candidateJiraId) throws Exception {
        Candidate candidate = candidateService.setupCandidate(candidateJiraId);

        return new ResponseEntity<>(candidate, HttpStatus.OK);
    }
}
