package com.team4.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "EVENT_LOG")
public class EventLog extends  EntityBase {

    private Double result;
    private Integer decision;
    private String comparisonStr;

    @OneToOne
    private EventType eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

    public EventLog() {
    }
}
