package com.team4.jpa.repository;

import com.team4.jpa.entity.Candidate;
import com.team4.jpa.entity.EventType;
import org.springframework.data.repository.CrudRepository;

public interface EventTypeRepository extends CrudRepository<EventType, Long> {
}
