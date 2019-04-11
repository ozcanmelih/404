package com.team4.service;

import com.team4.jpa.entity.Candidate;
import com.team4.jpa.repository.CandidateRepository;
import org.springframework.stereotype.Service;


@Service
public class CandidateService {

    private CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate findCandidateById(Long id) {
        return candidateRepository.findById(id).get();
    }
}
