package com.team4.jpa.repository;

import com.team4.jpa.entity.EventLog;
import com.team4.jpa.entity.EventType;
import org.springframework.data.repository.CrudRepository;

public interface EventLogRepository extends CrudRepository<EventLog, Long> {
}
