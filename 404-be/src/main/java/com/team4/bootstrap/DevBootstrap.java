package com.team4.bootstrap;

import com.team4.constants.EventTypeConstants;
import com.team4.jpa.entity.Candidate;
import com.team4.jpa.entity.EventType;
import com.team4.jpa.repository.CandidateRepository;
import com.team4.jpa.repository.EventLogRepository;
import com.team4.jpa.repository.EventTypeRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CandidateRepository candidateRepository;
    private EventTypeRepository eventTypeRepository;
    private EventLogRepository eventLogRepository;

    public DevBootstrap(CandidateRepository candidateRepository, EventTypeRepository eventTypeRepository, EventLogRepository eventLogRepository) {
        this.candidateRepository = candidateRepository;
        this.eventTypeRepository = eventTypeRepository;
        this.eventLogRepository = eventLogRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        EventType eventTypeFaceCount = new EventType(EventTypeConstants.FACE_COUNT, 5d, true);
        eventTypeRepository.save(eventTypeFaceCount);

        EventType eventTypeFaceMatch = new EventType(EventTypeConstants.FACE_MATCH, 20d, true);
        eventTypeRepository.save(eventTypeFaceMatch);

        Candidate candidate = new Candidate("Ahmet Melih", "Özcan", 6L, "https://scontent-mxp1-1.xx.fbcdn.net/v/t1.0-9/17191207_10154445428818526_6860830121595028979_n.jpg?_nc_cat=107&_nc_ht=scontent-mxp1-1.xx&oh=f74dcc6d5aff88776f748a116eb84262&oe=5D3C446F");
        candidateRepository.save(candidate);
    }
}
