package com.team4.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "EVENT_LOG")
public class EventLog extends EntityBase {

    private Double result;
    private Integer decision;
    private String comparisonStr;
    private String eventType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidate candidate;

    public EventLog() {
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Integer getDecision() {
        return decision;
    }

    public void setDecision(Integer decision) {
        this.decision = decision;
    }

    public String getComparisonStr() {
        return comparisonStr;
    }

    public void setComparisonStr(String comparisonStr) {
        this.comparisonStr = comparisonStr;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
