package com.team4.bootstrap;

import com.team4.jpa.entity.Candidate;
import com.team4.jpa.repository.CandidateRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CandidateRepository candidateRepository;

    public DevBootstrap(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        Candidate candidate = new Candidate("Ahmet Melih", "Özcan", 6L, "https://scontent-mxp1-1.xx.fbcdn.net/v/t1.0-9/17191207_10154445428818526_6860830121595028979_n.jpg?_nc_cat=107&_nc_ht=scontent-mxp1-1.xx&oh=f74dcc6d5aff88776f748a116eb84262&oe=5D3C446F");
        candidateRepository.save(candidate);
    }
}
