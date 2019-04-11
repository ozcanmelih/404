package com.team4.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_TYPE")
public class EventType extends EntityBase {

    private String description;
    private Double eventRate;
    private boolean active;

    public EventType() {
    }

    public EventType(String description, Double eventRate, boolean active) {
        this.description = description;
        this.eventRate = eventRate;
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getEventRate() {
        return eventRate;
    }

    public void setEventRate(Double eventRate) {
        this.eventRate = eventRate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
