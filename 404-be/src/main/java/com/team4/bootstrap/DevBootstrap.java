package com.team4.bootstrap;

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
        EventType eventType = new EventType("aaa", 5d, true);
        eventTypeRepository.save(eventType);
    }
}
