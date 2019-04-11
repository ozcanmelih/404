package com.team4.service;

import com.team4.io.IOUtil;
import com.team4.jpa.entity.Candidate;
import com.team4.jpa.repository.CandidateRepository;

import java.nio.file.Path;
import java.nio.file.Paths;

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
    
	public Candidate setupCandidate(String candidateJiraId) throws NumberFormatException{
		Long jiraId = new Long(candidateJiraId);
		//TODO dinamik olusturma eklenmeli 
		Path p = Paths.get(IOUtil.UPLOAD_ROOT_PATH.toString() , candidateJiraId, candidateJiraId + ".jpg");
		
		Candidate candidate = new Candidate("Mahmut", "Şakir", jiraId, p.toString());
		candidate = candidateRepository.save(candidate);
		
		return candidate;
	}
}
